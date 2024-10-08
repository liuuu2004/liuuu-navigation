package com.liuuu.admin.system.role.vo;

import com.liuuu.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@ApiModel("系统角色")
@Data
public class SysRoleVO extends BaseVO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("关联菜单ids")
    private List<Long> menuIds;
}
