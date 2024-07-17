package com.liuuu.common.core.exception;

import com.liuuu.common.core.constant.HttpStatus;
import lombok.Data;

import java.security.Provider;

/**
 * 服务异常
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */

@Data
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public ServiceException(String message) {
        super(message);
        this.code = HttpStatus.FAIL;
        this.message = message;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
        this.code = HttpStatus.FAIL;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
