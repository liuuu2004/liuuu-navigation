package com.liuuu.admin.system.log.operation.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志分页记录
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
@ApiModel("操作日志分页记录")
public class LogOperationPageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("模块名称")
    private String moduleName;

    @ApiModelProperty("操作ip地址")
    private String ipAddress;

    @ApiModelProperty("操作用户")
    private String operateUser;

    @ApiModelProperty("业务类型")
    private Integer businessType;

    @ApiModelProperty("操作类型")
    private Integer operateType;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("开始操作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginGmtOperate;

    @ApiModelProperty("结束操作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endGmtOperate;
}
