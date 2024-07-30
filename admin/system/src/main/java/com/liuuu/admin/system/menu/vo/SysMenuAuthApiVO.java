package com.liuuu.admin.system.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单分配接口
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Data
@ApiModel("菜单分配接口")
public class SysMenuAuthApiVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("接口id")
    private Long apiId;

    @ApiModelProperty("接口名称")
    private String apiName;

    @ApiModelProperty("接口分类id")
    private Long apiCategoryId;

    @ApiModelProperty("接口分类名称")
    private String apiCategoryName;

}
