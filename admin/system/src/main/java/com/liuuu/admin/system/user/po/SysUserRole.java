package com.liuuu.admin.system.user.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户角色
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("fk_user_id")
    private Long userId;

    @TableField("fk_role_id")
    private Long roleId;
}
