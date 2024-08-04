package com.liuuu.admin.system.dictionary.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据详情
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("字典数据详情")
public class DictionaryDataInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典数据id")
    private Long id;

    @ApiModelProperty("字典标签")
    private String dictionaryLabel;

    @ApiModelProperty("字典值")
    private String dictionaryValue;

    @ApiModelProperty("样式类型")
    private String classType;
}
