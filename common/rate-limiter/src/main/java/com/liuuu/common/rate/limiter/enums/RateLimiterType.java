package com.liuuu.common.rate.limiter.enums;

/**
 * 限流类型
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
public enum RateLimiterType {
    /**
     * 默认为全局限流
     */
    DEFAULT,

    /**
     * 根据请求IP进行限流
     */
    IP,

    /**
     * 根据用户限流
     */
    USER
}
