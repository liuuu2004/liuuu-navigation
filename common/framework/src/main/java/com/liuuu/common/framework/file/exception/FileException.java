package com.liuuu.common.framework.file.exception;

import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.exception.BaseException;

/**
 * 文件异常
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String message) {
        super(HttpStatus.FAIL, message, "file");
    }
}
