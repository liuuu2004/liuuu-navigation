package com.liuuu.admin.core.security.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录
 *
 * @Author Liuuu
 * @Date 2024/8/9
 */
@Data
@ApiModel("登录")
public class AuthLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("令牌")
    private String token;
}
