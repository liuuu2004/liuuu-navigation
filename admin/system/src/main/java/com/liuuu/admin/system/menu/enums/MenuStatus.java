package com.liuuu.admin.system.menu.enums;

/**
 * 菜单状态
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
public enum MenuStatus {
    NORMAL(1),
    DISABLE(2);

    public final Integer code;

    MenuStatus(Integer code) {
        this.code = code;
    }
}
