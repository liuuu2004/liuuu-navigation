package com.liuuu.admin.nav.site.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户查询列表
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
public class NavSiteListUserImportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("网站名称")
    private String siteName;

    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;
}
