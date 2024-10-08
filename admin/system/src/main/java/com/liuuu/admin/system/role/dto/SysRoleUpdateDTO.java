package com.liuuu.admin.system.role.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色修改
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Data
@ApiModel("角色修改")
public class SysRoleUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id", required = true)
    @NotNull(message = "角色id不能为空")
    private Long id;

    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色编码", required = true)
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "角色编码不能为空")
    private Integer sort;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("关联菜单ids")
    private List<Long> menuIds;
}
