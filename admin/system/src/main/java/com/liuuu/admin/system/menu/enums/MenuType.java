package com.liuuu.admin.system.menu.enums;


/**
 * 菜单类型
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
public enum MenuType {
    DIRECTORY("D"),
    MENU("M"),
    BUTTON("B");

    public final String code;

    MenuType(String code) {
        this.code = code;
    }
}
