package com.liuuu.admin.system.menu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 分配API
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Data
@ApiModel("分配API")
public class SysMenuAuthApiDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单id", required = true)
    @NotNull(message = "菜单id不能为空")
    private Long menuId;

    @ApiModelProperty("接口列表")
    private List<Long> apiIds;
}
