package com.liuuu.common.log.factory;

import com.liuuu.common.core.util.ip.AddressUtils;
import com.liuuu.common.core.util.spring.SpringUtils;
import com.liuuu.common.log.dto.LogDTO;
import com.liuuu.common.log.service.LogMqService;
import eu.bitwalker.useragentutils.UserAgent;
import com.liuuu.common.core.util.servlet.ServletUtils;
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
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        return new TimerTask() {
            @Override
            public void run() {
                String operateSystem = userAgent.getOperatingSystem().getName();
                String browser = userAgent.getBrowser().getName();
                logDTO.setOperateSystem(operateSystem);
                logDTO.setBrowserType(browser);
                logDTO.setOperateLocation(AddressUtils.getRealAddressByIP(logDTO.getIpAddress()));
                SpringUtils.getBean(LogMqService.class).addLogToMq(logDTO);
            }
        };
    }
}
