package com.liuuu.admin.system.api.controller;

import com.liuuu.admin.system.api.dto.SysApiAddDTO;
import com.liuuu.admin.system.api.dto.SysApiPageDTO;
import com.liuuu.admin.system.api.dto.SysApiUpdateDTO;
import com.liuuu.admin.system.api.mapstruct.SysApiConverter;
import com.liuuu.admin.system.api.po.SysApi;
import com.liuuu.admin.system.api.service.SysApiService;
import com.liuuu.admin.system.api.vo.SysApiVO;
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

/**
 * 系统接口
 *
 * @Author Liuuu
 * @Date 2024/7/31
 */
@Api(tags = "系统接口")
@RestController
@RequestMapping("/system/api")
public class SysApiController extends BaseController {
    @Autowired
    private SysApiService sysApiService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysApiVO>> page(@Valid SysApiPageDTO sysApiPageDTO) {
        sysApiPageDTO.setOrderColumn("sort,id");
        PageVO<SysApi> pageVO = sysApiService.page(sysApiPageDTO);
        return ResponseResult.success(SysApiConverter.INSTANCE.convert(pageVO));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysApiVO> getById(@PathVariable Long id) {
        return ResponseResult.success(SysApiConverter.INSTANCE.convert(sysApiService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统接口", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody SysApiAddDTO sysApiAddDTO) {
        SysApi sysApi = SysApiConverter.INSTANCE.convert(sysApiAddDTO);
        sysApiService.save(sysApi);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统接口", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody SysApiUpdateDTO sysApiUpdateDTO) {
        SysApi sysApi = SysApiConverter.INSTANCE.convert(sysApiUpdateDTO);
        sysApiService.updateById(sysApi);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统接口", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysApiService.removeByIds(ids);
        return ResponseResult.success();
    }

    @GetMapping("/getMaxSortByCategoryId")
    @ApiOperation("获取最大排序")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSortByCategoryId(Long apiCategoryId) {
        Integer maxSort = sysApiService.getMaxSortByCategoryId(apiCategoryId);
        return ResponseResult.success(maxSort);
    }
}
