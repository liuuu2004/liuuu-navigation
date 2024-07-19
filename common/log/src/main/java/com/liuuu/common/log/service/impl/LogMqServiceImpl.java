package com.liuuu.common.log.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.liuuu.common.log.constant.LogConstant;
import com.liuuu.common.log.dto.LogDTO;
import com.liuuu.common.log.service.LogMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 日志消息中心
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */
@Service
public class LogMqServiceImpl implements LogMqService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addLogToMq(LogDTO logDTO) {
        stringRedisTemplate.convertAndSend(LogConstant.LOG_MQ_TOPIC, JSONObject.toJSONString(logDTO));
    }

}