package com.liuuu.common.core.exception;

import lombok.Data;

/**
 * 服务异常
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */

@Data
public class ParamException extends RuntimeException {

    /**
     * 错误信息
     */
    private String message;

    public ParamException(String message) {
        super(message);
        this.message = message;
    }

    public ParamException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
