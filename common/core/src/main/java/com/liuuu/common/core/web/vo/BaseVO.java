package com.liuuu.common.core.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * 基本返回数据
 *
 * @Author Liuuu
 * @Data 2024/7/19
 */
@Data
@ApiModel("基本返回信息")
public class BaseVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("创建人用户id")
    private long createUserId;

    @ApiModelProperty("修改时间")
    private Date gmtModify;

    @ApiModelProperty("修改人用户id")
    private long modifyUserId;
}
