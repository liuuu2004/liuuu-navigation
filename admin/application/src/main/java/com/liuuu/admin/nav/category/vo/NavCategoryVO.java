package com.liuuu.admin.nav.category.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 导航分类
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Data
@ApiModel("导航分类")
public class NavCategoryVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类id")
    private Long id;

    @ApiModelProperty("上级id")
    private Long parentId;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("分类图标")
    private String categoryIcon;

    @ApiModelProperty("状态")
    private Integer status;
}
