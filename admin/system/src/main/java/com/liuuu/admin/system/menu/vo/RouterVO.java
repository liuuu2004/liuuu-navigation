package com.liuuu.admin.system.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 路由配置信息
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Data
@ApiModel("路由配置信息")
public class RouterVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("路由名字")
    private String name;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("设为true时不会在侧边栏隐藏路由")
    private boolean hidden;

    @ApiModelProperty("重定向地址")
    private String redirect;

    @ApiModelProperty("组件地址")
    private String component;

    @ApiModelProperty("路由参数")
    private String query;

    @ApiModelProperty("嵌套信息")
    private Boolean alwaysShow;

    @ApiModelProperty("其他元素")
    private MetaVO meta;

    @ApiModelProperty("子路由")
    private List<RouterVO> children;
}
