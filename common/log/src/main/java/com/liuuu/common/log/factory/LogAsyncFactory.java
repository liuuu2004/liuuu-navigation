package com.liuuu.common.log.factory;

import com.liuuu.common.log.dto.LogDTO;

import java.util.TimerTask;

/**
 * 日志异步工厂
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public class LogAsyncFactory {
    /**
     * 添加日志到Redis消息队列以免大量操作影响数据库性能
     * @param logDTO
     * @return
     */
    public static TimerTask addLog(LogDTO logDTO) {

    }
}
