package com.liuuu.common.rate.limiter.exception;

/**
 * 限流异常
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
public class RateLimiterException extends RuntimeException {
    private String message;

    public RateLimiterException(String message) {
        super(message);
        this.message = message;
    }

    public RateLimiterException(String message, Throwable t) {
        super(message, t);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
