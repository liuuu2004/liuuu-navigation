package com.liuuu.admin.system.param.controller;

import com.liuuu.admin.system.param.dto.SysParamAddDTO;
import com.liuuu.admin.system.param.dto.SysParamPageDTO;
import com.liuuu.admin.system.param.dto.SysParamUpdateDTO;
import com.liuuu.admin.system.param.mapstruct.SysParamConverter;
import com.liuuu.admin.system.param.po.SysParam;
import com.liuuu.admin.system.param.service.SysParamService;
import com.liuuu.admin.system.param.vo.SysParamVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.controller.BaseController;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 参数配置
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Api(tags = "参数配置")
@RestController
@RequestMapping("/system/param")
public class SysParamController extends BaseController {
    @Autowired
    private SysParamService sysParamService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysParamVO>> page(@Valid SysParamPageDTO pageDTO) {
        pageDTO.setOrderColumn("sort,id");
        PageVO<SysParam> pageVO = sysParamService.page(pageDTO);
        return ResponseResult.success(SysParamConverter.INSTANCE.convert(pageVO));
    }

    @ApiOperation("详情")
    @GetMapping("/getById/{id}")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysParamVO> getById(@PathVariable Long id) {
        return ResponseResult.success(SysParamConverter.INSTANCE.convert(sysParamService.getById(id)));
    }

    @ApiOperation("新增")
    @PutMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "参数配置", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @PathVariable SysParamAddDTO addDTO) {
        sysParamService.save(addDTO);
        return ResponseResult.success();
    }

    @ApiOperation("修改")
    @PutMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "参数配置", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @PathVariable SysParamUpdateDTO updateDTO) {
        sysParamService.updateById(updateDTO);
        return ResponseResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "参数配置", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysParamService.removeByIds(ids);
        return ResponseResult.success();
    }

    @ApiOperation("获取最大排序")
    @GetMapping("/getMaxSort")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = sysParamService.getMaxSort();
        return ResponseResult.success(maxSort);
    }
}
