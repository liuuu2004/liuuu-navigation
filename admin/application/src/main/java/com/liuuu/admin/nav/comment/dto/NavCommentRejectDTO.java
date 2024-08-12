package com.liuuu.admin.nav.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 评论驳回
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
@Data
@ApiModel("评论驳回")
public class NavCommentRejectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论ids")
    @NotEmpty(message = "评论ids不能为空")
    private List<Long> ids;

    @ApiModelProperty("备注(驳回原因)")
    private String remark;
}
