package com.liuuu.admin.system.menu.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Data
@ApiModel("系统菜单")
public class SysMenuVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    private Long id;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("上级菜单id")
    private Long parentId;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("菜单类型")
    private String menuType;

    @ApiModelProperty("权限标识")
    private String permissionCode;

    @ApiModelProperty("路由地址")
    private String routeUrl;

    @ApiModelProperty("组件路径")
    private String componentPath;

    @ApiModelProperty("路由参数")
    private String routeParam;

    @ApiModelProperty("是否为外链")
    private Integer hasFrame;

    @ApiModelProperty("是否缓存")
    private Integer hasCache;

    @ApiModelProperty("是否需要权限")
    private Integer hasPermission;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("显示状态")
    private Integer showStatus;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("子菜单")
    private List<SysMenuVO> children = new ArrayList<SysMenuVO>();

}
