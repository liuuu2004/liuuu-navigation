package com.liuuu.admin.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 接口修改
 *
 * @Author Liuuu
 * @Date 2024/7/31
 */
@Data
@ApiModel("接口修改")
public class SysApiUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接口id", required = true)
    @NotNull(message = "接口id不能为空")
    private Long id;

    @ApiModelProperty(value = "接口名称", required = true)
    @NotBlank(message = "接口名称不能为空")
    private String apiName;

    @ApiModelProperty(value = "接口地址", required = true)
    @NotBlank(message = "接口地址不能为空")
    private String apiUrl;

    @ApiModelProperty(value = "接口请求方式", required = true)
    @NotBlank(message = "接口请求方式不能为空")
    private String apiMethod;

    @ApiModelProperty(value = "所属分类", required = true)
    @NotNull(message = "所属分类不能为空")
    private Long apiCategoryId;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
