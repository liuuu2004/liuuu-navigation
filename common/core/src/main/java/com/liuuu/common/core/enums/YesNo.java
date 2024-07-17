package com.liuuu.common.core.enums;

/**
 * 是否枚举
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public enum YesNo {

    YES(1),
    NO(2);

    public final Integer code;
    YesNo(Integer code) {
        this.code  =code;
    }
}
