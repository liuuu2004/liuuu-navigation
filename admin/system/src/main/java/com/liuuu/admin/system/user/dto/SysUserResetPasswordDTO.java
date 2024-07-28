package com.liuuu.admin.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户重置密码
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Data
@ApiModel("用户重置密码")
public class SysUserResetPasswordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", required = true)
    @NotNull(message = "充值的用户id不能为空")
    private Long id;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "密码不能为空")
    private String password;
}
