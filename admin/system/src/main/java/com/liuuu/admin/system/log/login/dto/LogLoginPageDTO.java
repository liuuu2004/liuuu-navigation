package com.liuuu.admin.system.log.login.dto;

import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志分页查询
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
@ApiModel("登录日志分页查询")
public class LogLoginPageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登陆用户名")
    @Query(QueryWay.LIKE)
    private String username;

    @ApiModelProperty("登录ip地址")
    @Query(QueryWay.LIKE)
    private String ipAddress;

    @ApiModelProperty("登陆状态")
    private Integer status;

    @ApiModelProperty("开始登陆时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Query(value = QueryWay.GE, fieldName = "gmtLogin")
    private Date startGmtLogin;

    @ApiModelProperty("结束登陆时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Query(value = QueryWay.LE, fieldName = "gmtLogin")
    private Date endGmtLogin;
}
