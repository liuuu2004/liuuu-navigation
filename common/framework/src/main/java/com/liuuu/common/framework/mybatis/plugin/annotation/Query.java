package com.liuuu.common.framework.mybatis.plugin.annotation;


import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;

import java.lang.annotation.*;

/**
 * 查询注解
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Query {
    /**
     * 查询方式
     * @return
     */
    QueryWay value() default QueryWay.EQ;

    /**
     * 属性名
     * @return
     */
    String fieldName() default "";

    /**
     * 是否忽略查询
     * @return
     */
    boolean ignore() default false;

    /**
     * 是否空查询
     * @return
     */
    boolean empty() default false;
}
