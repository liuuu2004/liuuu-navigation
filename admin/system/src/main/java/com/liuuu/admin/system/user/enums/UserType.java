package com.liuuu.admin.system.user.enums;

/**
 * 用户类型
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
public enum UserType {

    ADMIN(1),
    NAV(2);

    public final Integer code;

    UserType(Integer code) {
        this.code = code;
    }
}
