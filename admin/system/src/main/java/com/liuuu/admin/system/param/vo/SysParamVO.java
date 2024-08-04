package com.liuuu.admin.system.param.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数配置
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("参数配置")
public class SysParamVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("参数id")
    private Long id;

    @ApiModelProperty("参数名称")
    private String paramName;

    @ApiModelProperty("参数键")
    private String paramKey;

    @ApiModelProperty("参数值")
    private String paramValue;

    @ApiModelProperty("参数类型")
    private Integer paramType;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;
}
