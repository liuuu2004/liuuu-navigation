package com.liuuu.admin.nav.comment.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 评论分页
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
@Data
@ApiModel("评论分页")
public class NavCommentPageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论内容")
    @Query(QueryWay.LIKE)
    private String commentContent;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long createUserId;
}
