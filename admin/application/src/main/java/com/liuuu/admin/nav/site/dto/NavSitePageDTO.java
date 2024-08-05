package com.liuuu.admin.nav.site.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 导航网站分页
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Data
@ApiModel("导航网站分页")
public class NavSitePageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("网站名称")
    @Query(ignore = true)
    private String siteNameDescription;

    @ApiModelProperty("状态")
    private Integer status;
}
