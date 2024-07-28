package com.liuuu.admin.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户修改
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Data
@ApiModel("用户修改")
public class SysUserAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

}
