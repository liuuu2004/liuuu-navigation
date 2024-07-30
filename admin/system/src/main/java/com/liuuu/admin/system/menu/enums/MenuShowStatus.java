package com.liuuu.admin.system.menu.enums;

/**
 * 显示状态
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
public enum MenuShowStatus {
    SHOW(1),
    HIDDEN(2);

    public Integer code;

    MenuShowStatus(Integer code) {
        this.code = code;
    }
}
