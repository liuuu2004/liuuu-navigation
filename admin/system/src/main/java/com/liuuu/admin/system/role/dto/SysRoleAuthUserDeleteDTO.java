package com.liuuu.admin.system.role.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 系统角色取消授权
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Data
@ApiModel("系统角色取消授权")
public class SysRoleAuthUserDeleteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id", required = true)
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @ApiModelProperty(value = "用户ids", required = true)
    @NotEmpty(message = "用户ids不能为空")
    private List<Long> userIds;
}
