package com.liuuu.common.core.enums;

/**
 * 删除枚举
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public enum DelFlag {

    NOT_DELETED(1),
    DELETED(2);

    public final Integer code;

    DelFlag(Integer code) {
        this.code = code;
    }

}
