package com.liuuu.admin.system.dictionary.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("字典")
public class SysDictionaryVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典id")
    private Long id;

    @ApiModelProperty("字典名称")
    private String dictionaryName;

    @ApiModelProperty("字典编码")
    private String dictionaryCode;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
