package com.liuuu.admin.client.nav.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("评论")
public class ClientNavCommentTreeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论id")
    private Long id;

    @ApiModelProperty("上级id")
    private Long parentId;

    @ApiModelProperty("是否置顶")
    private Integer hasSticky;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("创建人用户id")
    private Long createUserId;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("是否为站长")
    private Boolean webMaster;

    @ApiModelProperty("子评论")
    private List<ClientNavCommentTreeVO> children;
}
