package com.liuuu.admin.system.log.login.enums;

/**
 * 登录日志状态
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
public enum LogLoginStatus {
    SUCCESS(1),
    FAIL(2);

    public final Integer code;
    LogLoginStatus(Integer code) {
        this.code = code;
    }
}
