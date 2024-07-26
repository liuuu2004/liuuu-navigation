package com.liuuu.admin.system.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.liuuu.common.core.web.vo.BaseVO;

import java.io.Serializable;

/**
 * 接口
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@Data
@ApiModel("接口")
public class SysApiVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1l;

    @ApiModelProperty("接口id")
    private Long id;

    @ApiModelProperty("接口名称")
    private String apiName;

    @ApiModelProperty("接口地址")
    private String apiUrl;

    @ApiModelProperty("接口请求方式")
    private Long apiMethod;

    @ApiModelProperty("所属分类")
    private Long categoryId;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
