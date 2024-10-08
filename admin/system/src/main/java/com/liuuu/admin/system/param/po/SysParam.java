package com.liuuu.admin.system.param.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数配置
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
public class SysParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 参数id
     */
    @TableId
    private Long id;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数键
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数类型
     */
    private Integer paramType;

    /**
     * 排序
     */
    private Integer sort;

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
