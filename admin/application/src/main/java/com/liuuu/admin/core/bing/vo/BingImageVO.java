package com.liuuu.admin.core.bing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微软Bing图片
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("微软Bing图片")
public class BingImageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片链接")
    private String imageUrl;

    public BingImageVO(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
