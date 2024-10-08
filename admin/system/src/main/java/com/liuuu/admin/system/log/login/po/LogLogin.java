package com.liuuu.admin.system.log.login.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogLogin implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 登录日志id
     */
    @TableId
    private Long id;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 用户id
     */
    @TableField("fk_user_id")
    private Long userId;

    /**
     * 登录ip地址
     */
    private String ipAddress;

    /**
     * 登陆位置
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browserType;

    /**
     * 操作系统
     */
    private String operateSystem;

    /**
     * 登陆状态
     */
    private Integer status;

    /**
     * 提示消息
     */
    private String hintMessage;

    /**
     * 登陆时间
     */
    private Date gmtLogin;
}
