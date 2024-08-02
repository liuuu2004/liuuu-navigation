package com.liuuu.admin.system.user.controller;

import com.liuuu.admin.system.user.dto.*;
import com.liuuu.admin.system.user.mapstruct.SysUserConverter;
import com.liuuu.admin.system.user.po.SysUser;
import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.controller.BaseController;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.enums.BusinessType;
import com.liuuu.framework.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 系统用户
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/page")
    @ApiOperation("分页列表")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysUserVO>> page(SysUserPageDTO pageDTO) {
        PageVO<SysUserVO> pageVO = sysUserService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysUserVO> getById(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        if (user != null) {
            user.setPassword(null);
        }
        return ResponseResult.success(SysUserConverter.INSTANCE.convert(user));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody SysUserAddDTO addDTO) {
        sysUserService.add(addDTO);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody SysUserUpdateDTO updateDTO) {
        SysUser sysUser = SysUserConverter.INSTANCE.convert(updateDTO);
        sysUserService.updateById(sysUser);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysUserService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

    @PutMapping("/resetPassword")
    @ApiOperation("重置密码")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理-重置密码", businessType = BusinessType.UPDATE, isSaveRequestData = false)
    public ResponseResult resetPassword(@Valid @RequestBody SysUserResetPasswordDTO sysUserResetPasswordDTO) {
        sysUserService.resetPassword(sysUserResetPasswordDTO);
        return ResponseResult.success();
    }

    @PutMapping("/userUpdateInfo")
    @ApiOperation("用户修改信息")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理-用户修改信息", businessType = BusinessType.UPDATE)
    public ResponseResult userUpdateInfo(@Valid @RequestBody SySUserUpdateInfoDTO sySUserUpdateInfoDTO) {
        SysUser sysUser = SysUserConverter.INSTANCE.convert(sySUserUpdateInfoDTO);
        sysUser.setId(SecurityUtils.getUserId());
        sysUserService.updateById(sysUser);
        return ResponseResult.success();
    }

    @PutMapping("/resetUserPassword")
    @ApiOperation("重置密码")
    public ResponseResult resetUserPassword(@Valid @RequestBody SysUserResetUserPasswordDTO sysUserResetUserPasswordDTO) {
        sysUserService.resetUserPassword(sysUserResetUserPasswordDTO);
        return ResponseResult.success();
    }

}
