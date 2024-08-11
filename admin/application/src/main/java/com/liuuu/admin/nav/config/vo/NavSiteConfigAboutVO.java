package com.liuuu.admin.nav.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 关于本站
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Data
@ApiModel("关于本站")
public class NavSiteConfigAboutVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关于本站描述")
    private String aboutSiteDescription;

    @ApiModelProperty("关于本站邮箱")
    private String aboutSiteEmail;

    @ApiModelProperty("关于本站内容")
    private String aboutSiteContent;

    @ApiModelProperty("站长昵称")
    private String nickName;

    @ApiModelProperty("站长头像")
    private String avatar;
}
