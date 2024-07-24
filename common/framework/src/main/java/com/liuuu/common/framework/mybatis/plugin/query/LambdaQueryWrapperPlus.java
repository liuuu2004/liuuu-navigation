package com.liuuu.common.framework.mybatis.plugin.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.liuuu.common.core.util.string.StrUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 扩展LambdaQueryWrapper类
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
public class LambdaQueryWrapperPlus<T> extends LambdaQueryWrapper<T> {
    /**
     * 模糊查询
     * @param column
     * @param value
     * @return
     */
    public LambdaQueryWrapperPlus<T> likeIf(SFunction<T, ?> column, String value) {
        return StrUtils.isBlank(value) ? this : like(column, value);
    }

    @Override
    public LambdaQueryWrapperPlus<T> like(SFunction<T, ?> column, Object value) {
        return (LambdaQueryWrapperPlus<T>) super.like(column, value);
    }

    /**
     * 相等
     * @param column
     * @param value
     * @return
     */
    public LambdaQueryWrapperPlus<T> eqIf(SFunction<T, ?> column, Object value) {
        return StrUtils.isNullBlank(value) ? this : eq(column, value);
    }

    @Override
    public LambdaQueryWrapperPlus<T> eq(SFunction<T, ?> column, Object value) {
        return (LambdaQueryWrapperPlus<T>) super.eq(column, value);
    }

    /**
     * 包含
     * @param column
     * @param values
     * @return
     */
    public LambdaQueryWrapperPlus<T> inIf(SFunction<T, ?> column, Collection<?> values) {
        return CollectionUtils.isEmpty(values) ? this : in(column, values);
    }

    @Override
    public LambdaQueryWrapperPlus<T> in(SFunction<T, ?> column, Collection<?> values) {
        return (LambdaQueryWrapperPlus<T>) super.in(column, values);
    }

    /**
     * 不包含
     * @param column
     * @param values
     * @return
     */
    public LambdaQueryWrapperPlus<T> notInIf(SFunction<T, ?> column, Collection<?> values) {
        return CollectionUtils.isEmpty(values) ? this : notIn(column, values);
    }

    @Override
    public LambdaQueryWrapperPlus<T> notIn(SFunction<T, ?> column, Collection<?> values) {
        return (LambdaQueryWrapperPlus<T>) super.notIn(column, values);
    }

    /**
     * 小于
     * @param column
     * @param value
     * @return
     */
    public LambdaQueryWrapperPlus<T> ltIf(SFunction<T, ?> column, Object value) {
        return StrUtils.isNullBlank(value) ? this : lt(column, value);
    }

    @Override
    public LambdaQueryWrapperPlus<T> lt(SFunction<T, ?> column, Object value) {
        return (LambdaQueryWrapperPlus) super.lt(column, value);
    }

    /**
     * 小于等于
     * @param column
     * @param value
     * @return
     */
    public LambdaQueryWrapperPlus<T> leIf(SFunction<T, ?> column, Object value) {
        return StrUtils.isNullBlank(value) ? this : le(column, value);
    }

    @Override
    public LambdaQueryWrapperPlus<T> le(SFunction<T, ?> column, Object value) {
        return (LambdaQueryWrapperPlus<T>) super.le(column, value);
    }

    /**
     * 大于
     * @param column
     * @param value
     * @return
     */
    public LambdaQueryWrapperPlus<T> gtIf(SFunction<T, ?> column, Object value) {
        return StrUtils.isNullBlank(value) ? this : gt(column, value);
    }

    @Override
    public LambdaQueryWrapperPlus<T> gt(SFunction<T, ?> column, Object value) {
        return (LambdaQueryWrapperPlus<T>) super.gt(column, value);
    }

    /**
     * 大于等于
     * @param column
     * @param value
     * @return
     */
    public LambdaQueryWrapperPlus<T> geIf(SFunction<T, ?> column, Object value) {
        return StrUtils.isNullBlank(value) ? this : ge(column, value);
    }

    @Override
    public LambdaQueryWrapperPlus<T> ge(SFunction<T, ?> column, Object value) {
        return (LambdaQueryWrapperPlus<T>) super.ge(column, value);
    }
}
