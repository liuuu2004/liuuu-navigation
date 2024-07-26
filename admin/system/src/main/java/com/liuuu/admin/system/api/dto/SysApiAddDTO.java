package com.liuuu.admin.system.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 接口新增
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@Data
@ApiModel("接口新增")
public class SysApiAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接口名称", required = true)
    @NotBlank
    private String apiName;

    @ApiModelProperty(value = "接口地址", required = true)
    @NotBlank(message = "接口地址不能为空")
    private String apiUrl;

    @ApiModelProperty(value = "接口请求方式", required = true)
    @NotBlank(message = "接口请求方式不能为空")
    private Long apiMethod;

    @ApiModelProperty(value = "所属分类", required = true)
    @NotBlank(message = "所属分类不能为空")
    private Long apiCategoryId;

    @ApiModelProperty(value = "排序", required = true)
    @NotBlank(message = "排序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "状态", required = true)
    @NotBlank(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

}
