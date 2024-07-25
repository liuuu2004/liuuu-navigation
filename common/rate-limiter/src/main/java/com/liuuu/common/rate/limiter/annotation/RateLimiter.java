package com.liuuu.common.rate.limiter.annotation;

import com.liuuu.common.rate.limiter.enums.RateLimiterType;

import java.lang.annotation.*;

/**
 * 限流注解
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    String key() default "";

    RateLimiterType type() default RateLimiterType.DEFAULT;

    public int time() default 60;

    public int count() default 10;

    String hintMessage() default "操作频繁, 请稍后再试";
}
