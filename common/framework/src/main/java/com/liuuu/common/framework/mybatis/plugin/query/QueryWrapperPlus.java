package com.liuuu.common.framework.mybatis.plugin.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuuu.common.core.exception.ServiceException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
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
     * 排序字段分割表示
     */
    private static final String ORDER_BY_COLUMN_SPLIT = ",";

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
     * @param queryWrapperPlus
     * @param query
     * @param column
     * @param value
     */
    private void buildQueryCondition(QueryWrapperPlus queryWrapperPlus, Query query, String column, Object value) {

    }
}
