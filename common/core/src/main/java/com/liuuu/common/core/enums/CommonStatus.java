package com.liuuu.common.core.enums;

/**
 * 通用状态枚举
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public enum CommonStatus {

    NORMAL(1),
    DISABLE(2);

    public final Integer code;
    CommonStatus(Integer code) {
        this.code = code;
    }
}
