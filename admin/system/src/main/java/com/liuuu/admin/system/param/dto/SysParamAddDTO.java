package com.liuuu.admin.system.param.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 参数配置新增
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("参数配置新增")
public class SysParamAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数名称", required = true)
    @NotBlank(message = "参数名称不能为空")
    private String paramName;

    @ApiModelProperty(value = "参数键", required = true)
    @NotBlank(message = "参数键不能为空")
    private String paramKey;

    @ApiModelProperty(value = "参数值", required = true)
    @NotBlank(message = "参数值不能为空")
    private String paramValue;

    @ApiModelProperty("参数类型")
    private Integer paramType;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;
}
