package com.liuuu.admin.system.menu.vo;

import com.liuuu.common.core.util.string.StrUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 路由器信息
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Data
@ApiModel("路由器显示信息")
public class MetaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("路由器在侧边栏中显示的名字")
    private String title;

    @ApiModelProperty("路由器的图标")
    private String icon;

    @ApiModelProperty("若为true则不会被keep-alive缓存")
    private boolean noCache;

    @ApiModelProperty("内链地址")
    private String link;

    public MetaVO() {}

    public MetaVO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public MetaVO(String title, String icon, boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaVO(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public MetaVO(String title, String icon, boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (StrUtils.isHttp(link)) {
            this.link = link;
        }
    }
}
