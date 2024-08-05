package com.liuuu.admin.client.nav.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 导航客户端
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("导航客户端")
public class NavClientListVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类")
    private List<NavCategoryClientVO> categories;

    @ApiModelProperty("网站")
    private List<NavCategorySiteClientVO> sites;
}
