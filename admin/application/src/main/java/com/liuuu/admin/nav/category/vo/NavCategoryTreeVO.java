package com.liuuu.admin.nav.category.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 导航分类树形
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Data
@ApiModel("导航分类树形")
public class NavCategoryTreeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类id")
    private Long id;

    @ApiModelProperty("上级id")
    private Long parentId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("分类图标")
    private String categoryIcon;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("子分类")
    private List<NavCategoryTreeVO> children;
}
