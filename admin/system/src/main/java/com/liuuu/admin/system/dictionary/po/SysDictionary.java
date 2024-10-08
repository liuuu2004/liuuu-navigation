package com.liuuu.admin.system.dictionary.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统字典
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
public class SysDictionary implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    @TableId
    private Long id;

    /**
     * 字典名称
     */
    private String dictionaryName;

    /**
     * 字典编码
     */
    private String dictionaryCode;

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
