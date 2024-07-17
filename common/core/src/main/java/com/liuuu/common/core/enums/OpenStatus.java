package com.liuuu.common.core.enums;

/**
 * 开启状态
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public enum OpenStatus {

    YES(1),
    NO(2);

    public final Integer code;

    OpenStatus(Integer code) {
        this.code = code;
    }
}
