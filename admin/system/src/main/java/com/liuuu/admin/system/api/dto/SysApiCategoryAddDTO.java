package com.liuuu.admin.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 接口分类新增
 *
 * @Author Liuuu
 * @Date 2024/7/31
 */
@Data
@ApiModel
public class SysApiCategoryAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接口分类名称", required = true)
    @NotBlank(message = "接口分类名称不能为空")
    private String categoryName;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
