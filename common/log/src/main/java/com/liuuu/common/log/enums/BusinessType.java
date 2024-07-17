package com.liuuu.common.log.enums;

/**
 * 业务类型
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public enum BusinessType {
    /**
     * 其他
     */
    OTHER(1),
    /**
     * 新增
     */
    ADD(2),
    /**
     * 更新
     */
    UPDATE(3),
    /**
     * 删除
     */
    DELETE(4);

    private final Integer code;

    BusinessType(Integer code) {
        this.code = code;
    }
}
