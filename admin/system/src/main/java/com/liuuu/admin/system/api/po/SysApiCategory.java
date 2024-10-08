package com.liuuu.admin.system.api.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统接口分类
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Data
public class SysApiCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 接口id
     */
    @TableId
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人用户id
     */
    @TableField("fk_create_user_id")
    private Long createUserId;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 修改人用户id
     */
    @TableField("fk_modify_user_id")
    private Long modifyUserId;
}
