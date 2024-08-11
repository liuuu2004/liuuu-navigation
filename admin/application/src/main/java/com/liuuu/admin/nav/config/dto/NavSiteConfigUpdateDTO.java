package com.liuuu.admin.nav.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 网站配置更新
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Data
@ApiModel("网站配置更新")
public class NavSiteConfigUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "配置id")
    private Long id;

    @ApiModelProperty("关于本站描述")
    private String aboutSiteDescription;

    @ApiModelProperty("关于本站邮箱")
    private String aboutSiteEmail;

    @ApiModelProperty("关于本站内容")
    private String aboutSiteContent;

}
