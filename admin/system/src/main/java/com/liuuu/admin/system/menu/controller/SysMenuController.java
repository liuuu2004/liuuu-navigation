package com.liuuu.admin.system.menu.controller;

import com.liuuu.admin.system.api.service.SysApiService;
import com.liuuu.admin.system.menu.dto.SysMenuAddDTO;
import com.liuuu.admin.system.menu.dto.SysMenuAuthApiDTO;
import com.liuuu.admin.system.menu.dto.SysMenuListDTO;
import com.liuuu.admin.system.menu.dto.SysMenuUpdateDTO;
import com.liuuu.admin.system.menu.mapstruct.SysMenuConverter;
import com.liuuu.admin.system.menu.po.SysMenu;
import com.liuuu.admin.system.menu.service.SysMenuService;
import com.liuuu.admin.system.menu.vo.SysMenuAuthApiVO;
import com.liuuu.admin.system.menu.vo.SysMenuVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.enums.BusinessType;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLSimpleType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统菜单
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Api(tags = "系统菜单")
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysApiService sysApiService;

    @GetMapping("/list")
    @ApiOperation("列表")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<List<SysMenuVO>> list(SysMenuListDTO sysMenuListDTO) {
        List<SysMenuVO> list = sysMenuService.getMenuList(sysMenuListDTO);
        return ResponseResult.success(list);
    }

    @GetMapping("/tree")
    @ApiOperation("树形")
    @PreAuthorize("@auth.hasUrl")
    public ResponseResult<List<SysMenuVO>> tree() {
        List<SysMenuVO> list = sysMenuService.getMenuTree();
        return ResponseResult.success(list);
    }

    @GetMapping("getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysMenuVO> getById(@PathVariable Long id) {
        return ResponseResult.success(SysMenuConverter.INSTANCE.convert(sysMenuService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "菜单管理", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @PathVariable SysMenuAddDTO sysMenuAddDTO) {
        SysMenu sysMenu = SysMenuConverter.INSTANCE.convert(sysMenuAddDTO);
        sysMenuService.save(sysMenu);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "菜单管理", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @PathVariable SysMenuUpdateDTO sysMenuUpdateDTO) {
        SysMenu sysMenu = SysMenuConverter.INSTANCE.convert(sysMenuUpdateDTO);
        sysMenuService.updateById(sysMenu);
        return ResponseResult.success();
    }

    @GetMapping("/getMaxSortByParentId")
    @ApiOperation("获取最大排序")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSortByParentId(Long parentId) {
        Integer maxSort = sysMenuService.getMaxSortByParentId(parentId);
        return ResponseResult.success(maxSort);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "菜单管理", businessType = BusinessType.DELETE)
    public ResponseResult delete(@Valid @PathVariable Long id) {
        sysMenuService.remove(id);
        return ResponseResult.success();
    }

    @GetMapping("/auth/api/list")
    @ApiOperation("获取分配的api接口")
    public ResponseResult<List<SysMenuAuthApiVO>> getApiByMenuId(Long menuId) {
        List<SysMenuAuthApiVO> list = sysApiService.getAuthApiByMenuId(menuId);
        return ResponseResult.success(list);
    }

    @PostMapping("/auth/api")
    @ApiOperation("分配api")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "菜单管理-分配API", businessType = BusinessType.UPDATE)
    public ResponseResult authApi(@Valid @PathVariable SysMenuAuthApiDTO sysMenuAuthApiDTO) {
        sysMenuService.authApi(sysMenuAuthApiDTO);
        return ResponseResult.success();
    }
}
