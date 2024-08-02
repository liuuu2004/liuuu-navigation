package com.liuuu.admin.system.role.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色分配用户分页查询
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Data
@ApiModel("角色分配用户分页查询")
public class SysRoleAuthUserPageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id", required = true)
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("用户类型")
    private Integer userType;
}
