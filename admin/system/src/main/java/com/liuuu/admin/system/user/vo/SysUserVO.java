package com.liuuu.admin.system.user.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 系统用户
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Data
@ApiModel("系统用户")
public class SysUserVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("用户类型")
    private Integer userType;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("角色编码")
    private Set<String> roleCode;

    @ApiModelProperty("权限标识")
    private Set<String> permissionCodes;

    @ApiModelProperty("角色名称")
    private Set<String> roleNames;

}
