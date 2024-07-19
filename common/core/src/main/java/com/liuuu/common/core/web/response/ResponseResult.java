package com.liuuu.common.core.web.response;

import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.util.message.MessageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回前端统一响应
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */

@ApiModel("统一响应")
@Getter
@Setter
public class ResponseResult<T> {

    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("提示消息")
    private String message;

    @ApiModelProperty("数据")
    private T data;

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseResult success() {
        return success(null);
    }

    public static <T> ResponseResult success(T data) {
        return success(HttpStatus.SUCCESS, MessageUtils.message("operate.success"), data);
    }

    public static <T> ResponseResult success(String message, T data) {
        return success(HttpStatus.SUCCESS, message, data);
    }

    public static <T> ResponseResult success(int code, String message, T data) {
        return new ResponseResult(code, message, data);
    }

    public static ResponseResult fail(String message) {
        return fail(HttpStatus.FAIL, message);
    }

    public static ResponseResult fail(int code) {
        return fail(code, MessageUtils.message("operate.fail"));
    }

    public static ResponseResult fail(int code, String message) {
        return fail(code, message, null);
    }

    public static <T> ResponseResult fail(int code, String message, T data) {
        return new ResponseResult(code, message, data);
    }

}
