package com.liuuu.common.framework.mybatis.page.dto;

import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页对象
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
@ApiModel("分页对象")
@Data
public class PageDTO {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("每页显示记录数")
    @Query(ignore = true)
    private Long pageSize;

    @ApiModelProperty("起始页")
    @Query(ignore = true)
    private Long pageNum;

    @ApiModelProperty("排序列, 多个用逗号隔开")
    @Query(ignore = true)
    private String orderColumn;

    @ApiModelProperty("排序类型(asc/desc), 多个用逗号隔开")
    @Query(ignore = true)
    private String orderType;
}
