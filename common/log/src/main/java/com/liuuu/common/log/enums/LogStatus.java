package com.liuuu.common.log.enums;

/**
 * 日志状态
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public enum LogStatus {
    SUCCESS(1),
    ERROR(2);

    public final Integer code;
    LogStatus(Integer code) {
        this.code = code;
    }
}
