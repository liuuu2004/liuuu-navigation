package com.liuuu.admin.nav.picture.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 导航图片
 *
 * @Author Liuuu
 * @Date 2024/8/11
 */
@Data
public class NavPicture implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId
    private Long id;

    /**
     * 图片原名称
     */
    private String pictureOriginalName;

    /**
     * 图片路径
     */
    private String picturePath;

    /**
     * 图片md5
     */
    private String pictureMd5;

    /**
     * 创建人用户id
     */
    @TableField("fk_create_user_id")
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人用户id
     */
    @TableField("fk_modify_user_id")
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date gmtModify;
}
