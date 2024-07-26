package com.liuuu.admin.system.log.login.factory;

import com.liuuu.admin.system.log.login.po.LogLogin;
import com.liuuu.admin.system.log.login.service.LogLoginService;
import com.liuuu.common.core.util.ip.AddressUtils;
import com.liuuu.common.core.util.ip.IPUtils;
import com.liuuu.common.core.util.servlet.ServletUtils;
import com.liuuu.common.core.util.spring.SpringUtils;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.Date;
import java.util.TimerTask;

/**
 * 登录日志异步工厂
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
public class LogLoginAsyncFactory {
    /**
     * 记录日志
     * @param username  用户名
     * @param status  状态
     * @param hintMessage  提示消息
     * @return
     */
    public static TimerTask add(String username, Integer status, String hintMessage) {
        return add(null, username, status, hintMessage);
    }

    public static TimerTask add(Long userId, String username, Integer status, String hintMessage) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IPUtils.getIpAddress(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                String operateSystem = userAgent.getOperatingSystem().getName();
                String browser = userAgent.getBrowser().getName();
                LogLogin logLogin = LogLogin.builder()
                        .userId(userId)
                        .username(username)
                        .ipAddress(ip)
                        .loginLocation(address)
                        .operateSystem(operateSystem)
                        .browserType(browser)
                        .hintMessage(hintMessage)
                        .status(status)
                        .gmtLogin(new Date())
                        .build();
                SpringUtils.getBean(LogLoginService.class).save(logLogin);
            }
        };
    }
}
