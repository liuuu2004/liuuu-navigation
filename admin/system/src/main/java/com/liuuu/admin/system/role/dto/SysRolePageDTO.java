package com.liuuu.admin.system.role.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色分页查询
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Data
@ApiModel("角色分页查询")
public class SysRolePageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    @Query(QueryWay.LIKE)
    private String roleName;

    @ApiModelProperty("角色编码")
    @Query(QueryWay.LIKE)
    private String roleCode;

    @ApiModelProperty("状态")
    private Integer status;
}
