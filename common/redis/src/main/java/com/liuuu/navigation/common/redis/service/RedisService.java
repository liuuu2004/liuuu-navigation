package com.liuuu.navigation.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */

@Service
public class RedisService {

    /**
     * 限流脚本
     */
    private final String LIMIT_SCRIPT = "local key = KEYS[1]\n" +
            "local time = tonumber(ARGV[1])\n" +
            "local count = tonumber(ARGV[2])\n" +
            "local current = redis.call('get', key);\n" +
            "if current and tonumber(current) > count then\n" +
            "    return tonumber(current);\n" +
            "end\n" +
            "current = redis.call('incr', key)\n" +
            "if tonumber(current) == 1 then\n" +
            "    redis.call('expire', key, time)\n" +
            "end\n" +
            "return tonumber(current);";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存基本对象
     * @param key  缓存键
     * @param value  缓存值
     * @param <T>
     */
    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置缓存基本对象
     * @param key  缓存键
     * @param value  缓存值
     * @param expire  过期时间
     * @param timeUnit  时间单位
     * @param <T>
     */
    public <T> void set(String key, T value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    /**
     * 设置缓存有效期
     * @param key  缓存键
     * @param expire  缓存值
     * @return
     */
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存基本对象
     * @param key  缓存键
     * @return
     * @param <T>
     */
    public <T> T get(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 通配符获取缓存键
     * @param pattern
     * @return
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除缓存基本对象
     * @param key  缓存键
     * @return
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除缓存基本对象
     * @param collection  缓存键的集合
     * @return
     */
    public long delete(Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 获取缓存有效期
     * @param key  缓存键
     * @return
     */
    public Long getExpireTime(String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }

    /**
     * 执行限流脚本
     * @param key  缓存键
     * @param time  限流时间
     * @param count  限流次数
     * @return
     */
    public boolean limit(String key, int time, int count) {
        DefaultRedisScript<Long> limitScript = new DefaultRedisScript<>(LIMIT_SCRIPT, Long.class);
        Long currentRequestCount = (Long) redisTemplate.execute(limitScript, Arrays.asList(key), time, count);
        return currentRequestCount > count;
    }
    
}
