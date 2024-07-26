package com.liuuu.admin.system.api.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口分类
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@Data
@ApiModel("接口分类")
public class SysApiCategoryVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("接口分类id")
    private Long id;

    @ApiModelProperty("接口分类名称")
    private String categoryName;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

}
