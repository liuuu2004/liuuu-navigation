package com.liuuu.admin.system.menu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单新增
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Data
@ApiModel("菜单新增")
public class SysMenuAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单名称", required = true)
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    @ApiModelProperty("上级菜单id")
    private Long parentId;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单类型", required = true)
    @NotBlank(message = "请选择菜单类型")
    private Integer menuType;

    @ApiModelProperty("路由地址")
    private String routerUrl;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序顺序不能为空")
    private Integer sort;

    @ApiModelProperty("是否为外链")
    private Integer frame;

    @ApiModelProperty("是否缓存")
    private Integer cache;

    @ApiModelProperty("组件路径")
    private String componentPath;

    @ApiModelProperty("路由参数")
    private String routerParam;

    @ApiModelProperty("权限标识")
    private String permissionCode;

    @ApiModelProperty("显示状态")
    private Integer showStatus;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("是否需要权限")
    private Integer hasPermission;

}
