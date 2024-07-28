package com.liuuu.admin.system.user.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String username;

    private String nockName;

    private String password;

    private Integer userType;

    private Integer sex;

    private String email;

    private String mobilePhone;

    private String avatar;

    private Integer status;

    private String remark;

    private Integer delFlag;

    private Date gmtCreate;

    @TableField("fk_create_user_id")
    private Long createUserId;

    private Date gmtModify;

    @TableField("fk_modify_user_id")
    private Long modifyUserId;

}
