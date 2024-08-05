package com.liuuu.admin.client.nav.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 导航分类客户端列表
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("导航分类客户端列表")
public class NavCategoryClientVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类id")
    private Long id;

    @ApiModelProperty("上级id")
    private Long parentId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("分类图标")
    private String categoryIcon;

    @ApiModelProperty("子分类")
    private List<NavCategoryClientVO> children;
}
