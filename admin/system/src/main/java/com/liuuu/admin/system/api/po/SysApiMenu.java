package com.liuuu.admin.system.api.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口菜单
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
public class SysApiMenu implements Serializable {
    private static final Long serialVersionUID = 1L;

    /**
     * 接口菜单id
     */
    @TableId
    private Long id;

    /**
     * 接口id
     */
    @TableField("fk_api_id")
    private Long apiId;

    /**
     * 菜单id
     */
    @TableField
    private Long menuId;

}
