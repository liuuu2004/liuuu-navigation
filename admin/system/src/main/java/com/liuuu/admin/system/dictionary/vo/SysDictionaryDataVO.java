package com.liuuu.admin.system.dictionary.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("字典数据")
public class SysDictionaryDataVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典数据id")
    private Long id;

    @ApiModelProperty("字典标签")
    private String dictionaryLabel;

    @ApiModelProperty("字典值")
    private String dictionaryValue;

    @ApiModelProperty("所属字典 id")
    private String dictionaryId;

    @ApiModelProperty("样式类型")
    private String classType;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
