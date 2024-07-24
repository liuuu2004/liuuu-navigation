package com.liuuu.common.framework.mybatis.plugin.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.liuuu.common.core.exception.ServiceException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.constant.FieldConstant;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扩展QueryWrapper类, 提供更多功能
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
public class QueryWrapperPlus<T> extends QueryWrapper<T> {
    private static final Logger log = LoggerFactory.getLogger(QueryWrapperPlus.class);

    /**
     * 排序字段分割标识
     */
    private static final String ORDER_BY_COLUMN_SPLIT = ",";

    /**
     * 构建Wrapper查询条件
     * @param clazz  PO实体类
     * @param d  DTO实体参数对象
     * @param selectColumns  查询返回的列
     * @return
     * @param <D>
     */
    public <D> QueryWrapperPlus<T> buildQueryWrapper(Class<T> clazz, D d, SFunction<T, ?>... selectColumns) {
        QueryWrapperPlus queryWrapperPlus = new QueryWrapperPlus();

        queryWrapperPlus.lambda().select(selectColumns);
        if (d == null) {
            return queryWrapperPlus;
        }
        // 获取T属性和列名
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        if (tableInfo == null) {
            log.error("没有找到数据表对应的实体类, 当前传入的Clazz: {}", clazz);
            throw new ServiceException(MessageUtils.message("system.exception"));
        }
        // T属性和列名的对应关系
        Map<String, String> propertyColumnMap = new HashMap<>(tableInfo.getFieldList().size());
        tableInfo.getFieldList().forEach(table -> {
            propertyColumnMap.put(table.getProperty(), table.getColumn());
        });
        // 若主键不在fieldList中则单独获取
        if (StrUtils.isNotBlank(tableInfo.getKeyProperty())) {
            propertyColumnMap.put(tableInfo.getKeyProperty(), tableInfo.getKeyColumn());
        }
        // 获取d属性
        List<Field> fieldList = ReflectionKit.getFieldList(ClassUtils.getUserClass(d.getClass()));
        for (Field field : fieldList) {
            field.setAccessible(true);
            Query query = field.getAnnotation(Query.class);

            String fieldName = getQueryFieldName(query, field);
            if (query == null || query.ignore()) {
                continue;
            }
            verifyQueryFieldNameExist(fieldName, propertyColumnMap);
            Object value = getFieldValue(field, d);
            if (StrUtils.isNullBlank(value) && (query == null || !query.empty())) {
                continue;
            }
            String column = propertyColumnMap.get(fieldName);
            buildQueryCondition(queryWrapperPlus, query, column, value);
        }
        buildOrderByCondition(queryWrapperPlus, propertyColumnMap, fieldList, d);
        return queryWrapperPlus;
    }

    /**
     * 获取查询属性名称
     * @param query
     * @param field
     * @return
     */
    private String getQueryFieldName(Query query, Field field) {
        return query != null && StrUtils.isNotBlank(query.fieldName()) ? query.fieldName() : field.getName();
    }

    /**
     * 校验查询属性是否存在T实体类
     * @param fieldName
     * @param propertyColumnMap
     */
    private void verifyQueryFieldNameExist(String fieldName, Map<String, String> propertyColumnMap) {
        if (!propertyColumnMap.containsKey(fieldName)) {
            String message = StrUtils.format("查询条件字段: {fieldName} 不存在", fieldName);
            throw new ServiceException(message);
        }
    }

    /**
     * 获取属性值
     * @param field
     * @param d
     * @return
     * @param <D>
     */
    private <D> Object getFieldValue(Field field, D d) {
        if (field == null) {
            return null;
        }
        Object value = null;
        try {
            value = field.get(d);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ServiceException(MessageUtils.message("system.exception"));
        }
        return value;
    }

    /**
     * 构建查询条件
     * @param queryWrapperPlus  查询构造器
     * @param query  查询注释
     * @param column  列名称
     * @param value  值
     */
    private void buildQueryCondition(QueryWrapperPlus queryWrapperPlus, Query query, String column, Object value) {
        if (query == null || query.value() == null) {
            queryWrapperPlus.eq(column, value);
            return;
        }
        QueryWay queryWay = query.value();
        switch (queryWay) {
            case EQ:
                queryWrapperPlus.eq(column, value);
                break;
            case NE:
                queryWrapperPlus.ne(column, value);
                break;
            case GT:
                queryWrapperPlus.gt(column, value);
                break;
            case GE:
                queryWrapperPlus.ge(column, value);
                break;
            case LT:
                queryWrapperPlus.lt(column, value);
                break;
            case LE:
                queryWrapperPlus.le(column, value);
                break;
            case LIKE:
                queryWrapperPlus.like(column, value);
                break;
            case NOT_LIKE:
                queryWrapperPlus.notLike(column, value);
                break;
            case LIKE_LEFT:
                queryWrapperPlus.likeLeft(column, value);
                break;
            case LIKE_RIGHT:
                queryWrapperPlus.likeRight(column, value);
                break;
            case IN:
                if (value instanceof Collection) {
                    Collection coll = (Collection) value;
                    queryWrapperPlus.in(column, coll);
                } else {
                    queryWrapperPlus.in(column, value);
                }
                break;
            case NOT_IN:
                if (value instanceof Collection) {
                    Collection coll = (Collection) value;
                    queryWrapperPlus.notIn(column, coll);
                } else {
                    queryWrapperPlus.notIn(column, value);
                }
                break;
            default:
                queryWrapperPlus.eq(column, value);
        }
    }

    /**
     * 构造排序
     * @param queryWrapperPlus  查询构造器
     * @param propertyColumnMap  属性和列名
     * @param fieldList  属性列表
     * @param d  参数
     * @param <D>
     */
    private <D> void buildOrderByCondition(QueryWrapperPlus queryWrapperPlus, Map<String, String> propertyColumnMap,
                                           List<Field> fieldList, D d) {
        // 排序列
        Field orderColumnField = fieldList.stream().filter(f -> FieldConstant.ORDER_COLUMN.equals(f.getName())).findFirst().orElse(null);
        Object orderColumnObject = getFieldValue(orderColumnField, d);
        if (!(orderColumnObject instanceof String) || StrUtils.isNullBlank(orderColumnObject)) {
            return;
        }

        // 排序类型
        Field orderTypeField = fieldList.stream().filter(f -> FieldConstant.ORDER_TYPE.equals(f.getName())).findFirst().orElse(null);
        Object orderTypeObject = getFieldValue(orderTypeField, d);

        String[] orderColumns = orderColumnObject.toString().split(ORDER_BY_COLUMN_SPLIT);
        String[] orderTypes = orderTypeObject == null ? new String[] {} : orderTypeObject.toString().split(ORDER_BY_COLUMN_SPLIT);

        // 构造排序
        for (int i = 0; i < orderColumns.length; i++) {
            String orderColumn = orderColumns[i];
            if (StrUtils.isBlank(orderColumn)) {
                continue;
            }
            String column =propertyColumnMap.get(orderColumn);
            if (StrUtils.isBlank(column)) {
                String message = StrUtils.format("排序字段: {}, 不存在", orderColumn);
                throw new ServiceException(message);
            }
            boolean isAsc = i < orderTypes.length ? SqlKeyword.ASC.getSqlSegment().equalsIgnoreCase(orderTypes[i]) : true;
            queryWrapperPlus.orderBy(true, isAsc, column);
        }

    }
}
