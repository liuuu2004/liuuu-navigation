package com.liuuu.admin.system.log.login.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
public class LogLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录日志id")
    private Long id;

    @ApiModelProperty("登陆用户名")
    private String username;

    @ApiModelProperty("登录ip地址")
    private String ipAddress;

    @ApiModelProperty("登陆位置")
    private String loginLocation;

    @ApiModelProperty("浏览器类型")
    private String browserType;

    @ApiModelProperty("操作系统")
    private String operateSystem;

    @ApiModelProperty("登陆状态")
    private Integer status;

    @ApiModelProperty("提示消息")
    private String hintMessage;

    @ApiModelProperty("登陆时间")
    private Date gmtLogin;

}
