package com.liuuu.common.framework.mybatis.page.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
@Data
@ApiModel("分页返回")
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("数据列表")
    private List<T> list;

    @ApiModelProperty("总记录数")
    private long total;
}
