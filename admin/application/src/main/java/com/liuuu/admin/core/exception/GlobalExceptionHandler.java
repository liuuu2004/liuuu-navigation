package com.liuuu.admin.core.exception;

import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.exception.BaseException;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.exception.ServiceException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.rate.limiter.exception.RateLimiterException;

import kotlin.io.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局异常处理
 *
 * @Author liuuu
 * @Date 2024/8/8
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求参数校验不通过,兼容不同注解
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseResult.fail(HttpStatus.BAD_PARAM, message);
    }

    /**
     * 请求参数校验不通过,兼容不同注解
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult handleBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseResult.fail(HttpStatus.BAD_PARAM, message);
    }

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(ParamException.class)
    public ResponseResult paramException(ParamException e) {
        return ResponseResult.fail(HttpStatus.BAD_PARAM, e.getMessage());
    }

    /**
     * 不支持请求方式
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                              HttpServletRequest request) {
        String requestUTI = request.getRequestURI();
        log.error("请求地址 {}, 不支持 {} 请求", requestUTI, e.getMethod());
        return ResponseResult.fail(HttpStatus.BAD_METHOD, MessageUtils.message("bad.request.method"));
    }

    /**
     * 无权限访问
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult accessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage());
        return ResponseResult.fail(HttpStatus.FORBIDDEN, MessageUtils.message("security.forbidden"));
    }

    /**
     * 服务异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult serviceException(ServiceException e) {
        e.printStackTrace();
        return ResponseResult.fail(e.getCode(), e.getMessage());
    }

    /**
     * 自定义基本异常
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult baseException(BaseException e) {
        log.error("所属模块: {}, 异常信息: {}", e.getModule(), e.getMessage());
        return ResponseResult.fail(e.getCode(), e.getMessage());
    }

    /**
     * 限流
     * @param e
     * @return
     */
    @ExceptionHandler(RateLimiterException.class)
    public ResponseResult rateLimiterException(RateLimiterException e) {
        return ResponseResult.fail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    /**
     * 其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        e.printStackTrace();
        return ResponseResult.fail(MessageUtils.message("system.exception"));
    }


}
