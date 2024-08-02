package com.liuuu.admin.system.role.controller;

import com.liuuu.admin.system.role.dto.SysRolePageDTO;
import com.liuuu.admin.system.role.mapstruct.SysRoleConverter;
import com.liuuu.admin.system.role.po.SysRole;
import com.liuuu.admin.system.role.service.SysRoleService;
import com.liuuu.admin.system.role.vo.SysRoleVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 系统角色
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Api(tags = "系统角色")
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysRoleVO>> page(SysRolePageDTO pageDTO) {
        pageDTO.setOrderColumn("sort,id");
        PageVO<SysRole> pageVO = sysRoleService.page(pageDTO);
        return ResponseResult.success(SysRoleConverter.INSTANCE.convert(pageVO));
    }

    // TODO
    @GetMapping
}
