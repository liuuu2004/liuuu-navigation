package com.liuuu.common.rate.limiter.aspect;

import com.liuuu.common.core.util.ip.IPUtils;
import com.liuuu.common.core.util.servlet.ServletUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.rate.limiter.annotation.RateLimiter;
import com.liuuu.common.rate.limiter.constant.RateLimiterConstant;
import com.liuuu.common.rate.limiter.exception.RateLimiterException;
import com.liuuu.framework.security.util.SecurityUtils;
import com.liuuu.navigation.common.redis.service.RedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

/**
 * 限流
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@Component
@Aspect
public class RateLimiterAspect {
    @Autowired
    private RedisService redisService;

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint joinPoint, RateLimiter rateLimiter) {
        String limitKey = getLimitKey(joinPoint, rateLimiter);
        boolean isLimit = redisService.limit(limitKey, rateLimiter.time(), rateLimiter.count());
        if (isLimit) {
            throw new RateLimiterException(rateLimiter.hintMessage());
        }
    }

    private String getLimitKey(JoinPoint joinPoint, RateLimiter rateLimiter) {
        if (StrUtils.isNotBlank(rateLimiter.key())) {
            return RateLimiterConstant.CACHE_PREFIX + rateLimiter.key();
        }
        // 获取注解作用域的类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String limitKey = RateLimiterConstant.CACHE_PREFIX + className.replace(".", ":") + methodName;
        switch (rateLimiter.type()) {
            case IP:
                String ip = IPUtils.getIpAddress(ServletUtils.getRequest());
                return limitKey + ":" + ip.split(",")[0];
            case USER:
                return limitKey + ":" + SecurityUtils.getUserId();
            default:
                return limitKey;
        }
    }
}
