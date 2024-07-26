package com.liuuu.admin.system.log.operation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志操作
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
@ApiModel("日志操作")
public class LogOperationVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("操作日志id")
    private Long id;

    @ApiModelProperty("模块名称")
    private String moduleName;

    @ApiModelProperty("业务类型")
    private Integer businessType;

    @ApiModelProperty("操作类型")
    private Integer operateType;

    @ApiModelProperty("操作用户id")
    private Long userId;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("类方法")
    private String classMethod;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("操作ip地址")
    private String ipAddress;

    @ApiModelProperty("操作位置")
    private String operateLocation;

    @ApiModelProperty("请求参数")
    private String requestParam;

    @ApiModelProperty("返回结果")
    private String returnResult;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("操作时间")
    private Date gmtOperate;

    @ApiModelProperty("错误信息")
    private String errorMessage;

    @ApiModelProperty("浏览器类型")
    private String browserType;

    @ApiModelProperty("操作系统")
    private String operateSystem;

    @ApiModelProperty("操作人用户名")
    private String username;

    @ApiModelProperty("操作人昵称")
    private String nickName;

}
