package com.liuuu.common.log.enums;

/**
 * 操作类型
 *
 * @Author Liuuu
 * @Date 2022/7/17
 */
public enum OperateType {
    /**
     * 其他
     */
    OTHER(1),
    /**
     * 后台
     */
    ADMIN(2),
    /**
     * 移动端
     */
    MOBILE(3),
    /**
     * 导航
     */
    NAV(4);

    public final Integer code;

    OperateType(Integer code) {
        this.code = code;
    }
}
