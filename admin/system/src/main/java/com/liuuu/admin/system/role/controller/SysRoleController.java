package com.liuuu.admin.system.role.controller;

import com.liuuu.admin.system.role.dto.*;
import com.liuuu.admin.system.role.mapstruct.SysRoleConverter;
import com.liuuu.admin.system.role.po.SysRole;
import com.liuuu.admin.system.role.service.SysRoleService;
import com.liuuu.admin.system.role.vo.SysRoleVO;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.controller.BaseController;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @ApiOperation("详情")
    @GetMapping("/getbyId/{id}")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysRoleVO> getById(@PathVariable Long id) {
        SysRoleVO sysRoleVO = sysRoleService.getRoleById(id);
        return ResponseResult.success(sysRoleVO);
    }

    @ApiOperation("新增")
    @PostMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "角色管理", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody SysRoleAddDTO addDTO) {
        sysRoleService.add(addDTO);
        return ResponseResult.success();
    }

    @ApiOperation("修改")
    @PutMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "角色管理", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody SysRoleUpdateDTO updateDTO) {
        sysRoleService.update(updateDTO);
        return ResponseResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "角色管理", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysRoleService.removeByIds(ids);
        return ResponseResult.success();
    }

    @ApiOperation("获取最大排序")
    @GetMapping("/getMaxSort")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = sysRoleService.getMaxSort();
        return ResponseResult.success(maxSort);
    }

    @ApiOperation("已分配用户分页")
    @GetMapping("/auth/user/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysUserVO> getUserAuthPage(@Valid SysRoleAuthUserPageDTO pageDTO) {
        PageVO<SysUserVO> pageVO = sysRoleService.getUserAuthPage(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("未分配用户分页")
    @GetMapping("/auth/user/not/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysUserVO> getUserUnAuthPage(@Valid SysRoleNotAuthUserPageDTO pageDTO) {
        PageVO<SysUserVO> pageVO = sysRoleService.getUserUnAuthPage(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("授权用户")
    @PostMapping("/auth/user")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "角色管理-授权用户", businessType = BusinessType.ADD)
    public ResponseResult authUser(@Valid @RequestBody SysRoleAuthUserDTO authUserDTO) {
        sysRoleService.authUser(authUserDTO);
        return ResponseResult.success();
    }

    @ApiOperation("取消授权")
    @DeleteMapping("/auth/user/delete")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "角色管理-取消授权", businessType = BusinessType.DELETE)
    public ResponseResult deleteAuthUser(@Valid @RequestBody SysRoleAuthUserDeleteDTO deleteDTO) {
        sysRoleService.removeAuthUser(deleteDTO);
        return ResponseResult.success();
    }

}
