package com.liuuu.admin.nav.comment.enums;


/**
 * 评论状态
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
public enum NavCommentStatus {
    /**
     * 审核中
     */
    AUDIT(1),

    /**
     * 已通过
     */
    PASS(2),

    /**
     * 已拒绝
     */
    REJECT(3)

    ;

    public final Integer code;

    NavCommentStatus(Integer code) {
        this.code = code;
    }
}
