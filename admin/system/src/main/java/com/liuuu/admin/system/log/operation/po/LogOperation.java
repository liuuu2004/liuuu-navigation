package com.liuuu.admin.system.log.operation.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
public class LogOperation implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String moduleName;

    private Integer businessType;

    private Integer operateType;

    @TableField("fk_user_id")
    private Long userId;

    private String requestMethod;

    private String classMethod;

    private String requestUrl;

    private String ipAddress;

    private String operateLocation;

    private String requestParam;

    private String returnResult;

    private Integer status;

    private Date gmtOperate;

    private String errorMessage;

    private String browserType;

    private String operateSystem;
}
