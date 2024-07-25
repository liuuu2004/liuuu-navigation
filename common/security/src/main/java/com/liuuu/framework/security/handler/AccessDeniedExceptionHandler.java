package com.liuuu.framework.security.handler;

import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.web.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * 无权限一场全局捕获
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@RestControllerAdvice
public class AccessDeniedExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(AccessDeniedExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handlerAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return ResponseResult.fail(HttpStatus.FORBIDDEN, MessageUtils.message("security.forbidden"));
    }
}
