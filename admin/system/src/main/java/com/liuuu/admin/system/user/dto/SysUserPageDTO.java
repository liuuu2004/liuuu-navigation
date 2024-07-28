package com.liuuu.admin.system.user.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户分页查询
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Data
@ApiModel("用户分页查询")
public class SysUserPageDTO extends PageDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    @Query(QueryWay.LIKE)
    private String username;

    @ApiModelProperty("昵称")
    @Query(QueryWay.LIKE)
    private String nickName;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("用户类型")
    private Integer userType;

    @ApiModelProperty("开始创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm::ss")
    @Query(value = QueryWay.GE, fieldName = "gmtCreate")
    private Date beginGmtCreate;

    @ApiModelProperty("结束创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Query(value = QueryWay.LE, fieldName = "gmtCreate")
    private Date endGmtCreate;

    @ApiModelProperty(value = "用户id集合", hidden = true)
    @Query(value = QueryWay.IN, fieldName = "id")
    private List<Long> ids;

    @ApiModelProperty(value = "排除用户id集合", hidden = true)
    @Query(value = QueryWay.NOT_IN, fieldName = "id")
    private List<Long> notIds;
}
