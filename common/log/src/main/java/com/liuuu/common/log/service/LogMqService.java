package com.liuuu.common.log.service;

import com.liuuu.common.log.dto.LogDTO;

/**
 * 日志消息中心
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */
public interface LogMqService {

    /**
     * 添加日志消息中心
     * @param logDTO
     */
    void addLogToMq(LogDTO logDTO);
}
