package com.liuuu.admin.system.menu.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String menuName;

    private Long parentId;

    private Integer sort;

    private String menuType;

    private String permissionCode;

    private String routerUrl;

    private String componentPath;

    private String routerParam;

    private Integer hasFrame;

    private Integer hasCache;

    private Integer hasPermission;

    private String menuIcon;

    private Integer showStatus;

    private Integer status;

    @TableField("fk_create_user_id")
    private Long createUserId;

    private Date gmtCreate;

    @TableField("fk_modify_user_id")
    private Long modifyUserId;

    private Date gmtModify;
}
