package com.liuuu.common.framework.mybatis.plugin.interceptor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liuuu.common.core.util.id.IdWorker;
import com.liuuu.common.framework.mybatis.plugin.constant.FieldConstant;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * MyBatis拦截器
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
public class InsertUpdateMyBatisInterceptor {
    /**
     * 调用拦截
     * @param invocation  调用器
     * @param currentUserId  当前用户ID
     * @return
     * @throws Throwable
     */
    public static Object intercept(Invocation invocation, Long currentUserId) throws Throwable {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = statement.getSqlCommandType();
        if (!SqlCommandType.INSERT.equals(sqlCommandType) && !(SqlCommandType.UPDATE.equals(sqlCommandType))) {
            return invocation.proceed();
        }

        Object parameter = invocation.getArgs()[1];
        if (parameter == null) {
            return invocation.proceed();
        }

        Object clazz = null;
        if (parameter instanceof MapperMethod.ParamMap) {
            String paramKey = "param1";
            String paramEtKey = "et";
            if (((Map) parameter).containsKey(paramKey)) {
                clazz = ((Map) parameter).get(paramKey);
            } else if (((Map) parameter).containsKey(paramEtKey)) {
                clazz = ((Map) parameter).get(paramEtKey);
            }
        } else {
            clazz = parameter;
        }
        setParameter(sqlCommandType, clazz, currentUserId);
        return invocation.proceed();
    }

    /**
     * 设置参数, 省去每次都要手动创建或更新时间和用户ID信息的麻烦
     * @param sqlCommandType  SQL语句类型
     * @param clazz  参数对象
     * @param currentUserId  当前用户ID
     */
    private static void setParameter(SqlCommandType sqlCommandType, Object clazz, Long currentUserId) {
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            setParameterIdValue(clazz);
            setParameterValue(clazz, FieldConstant.GMT_CREATE, new Date());
            setParameterValue(clazz, FieldConstant.CREATE_USER_ID, currentUserId);
        } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            setParameterValue(clazz, FieldConstant.GMT_MODIFY, new Date());
            setParameterValue(clazz, FieldConstant.MODIFY_USER_ID, currentUserId);
        }
    }

    /**
     * 设置参数值
     * @param clazz  参数对象类
     * @param fieldName  属性名称
     * @param value  属性值
     */
    private static void setParameterValue(Object clazz, String fieldName, Object value) {
        Field field = null;
        try {
            field = clazz.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return;
        }

        field.setAccessible(true);
        try {
            field.set(clazz, value);
        } catch (IllegalAccessException e) {
            return;
        }
    }

    /**
     * 设置主键值
     * @param clazz
     */
    private static void setParameterIdValue(Object clazz) {
        Field field = null;
        try {
            field = clazz.getClass().getDeclaredField(FieldConstant.ID);
        } catch (NoSuchFieldException e) {
            return;
        }

        field.setAccessible(true);
        try {
            Object value = field.get(clazz);
            if (value != null) {
                return;
            }
            TableId tableId = field.getAnnotation(TableId.class);
            if (tableId != null && tableId.type() != null && IdType.AUTO.getKey() == tableId.type().getKey()) {
                return;
            }
            field.set(clazz, IdWorker.nextId());
        } catch (IllegalAccessException e) {
            return;
        }
    }
}
