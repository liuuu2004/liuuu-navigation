package com.liuuu.admin.nav.picture.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图片上传
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("图片上传")
public class NavPictureUploadVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("图片路径")
    private String filePath;
}
