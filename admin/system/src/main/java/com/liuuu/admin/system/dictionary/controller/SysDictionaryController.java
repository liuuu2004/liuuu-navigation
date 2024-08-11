package com.liuuu.admin.system.dictionary.controller;

import com.liuuu.admin.system.dictionary.dto.SysDictionaryAddDTO;
import com.liuuu.admin.system.dictionary.dto.SysDictionaryPageDTO;
import com.liuuu.admin.system.dictionary.dto.SysDictionaryUpdateDTO;
import com.liuuu.admin.system.dictionary.mapstruct.SysDictionaryConverter;
import com.liuuu.admin.system.dictionary.po.SysDictionary;
import com.liuuu.admin.system.dictionary.service.SysDictionaryService;
import com.liuuu.admin.system.dictionary.vo.DictionaryInfoVO;
import com.liuuu.admin.system.dictionary.vo.SysDictionaryVO;
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
import java.util.List;

/**
 * 系统字典
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Api(tags = "系统字典")
@RestController
@RequestMapping("/system/dictionary")
public class SysDictionaryController extends BaseController {
    @Autowired
    private SysDictionaryService sysDictionaryService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysDictionaryVO>> page(SysDictionaryPageDTO pageDTO) {
        pageDTO.setOrderColumn("sort,id");
        PageVO<SysDictionary> pageVO = sysDictionaryService.page(pageDTO);
        return ResponseResult.success(SysDictionaryConverter.INSTANCE.convert(pageVO));
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<List<SysDictionaryVO>> list() {
        return ResponseResult.success(sysDictionaryService.listSort());
    }

    @ApiOperation("详情")
    @GetMapping("/getById/{id}")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysDictionaryVO> getById(@PathVariable Long id) {
        return ResponseResult.success(SysDictionaryConverter.INSTANCE.convert(sysDictionaryService.getById(id)));
    }

    @ApiOperation("新增")
    @PostMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统字典", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody SysDictionaryAddDTO addDTO) {
        SysDictionary sysDictionary = SysDictionaryConverter.INSTANCE.convert(addDTO);
        sysDictionaryService.save(sysDictionary);
        return ResponseResult.success();
    }

    @ApiOperation("修改")
    @PutMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统字典", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody SysDictionaryUpdateDTO updateDTO) {
        SysDictionary sysDictionary = SysDictionaryConverter.INSTANCE.convert(updateDTO);
        sysDictionaryService.updateById(sysDictionary);
        return ResponseResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统字典", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysDictionaryService.removeByIds(ids);
        return ResponseResult.success();
    }

    @ApiOperation("获取最大排序")
    @GetMapping("/getMaxSort")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = sysDictionaryService.getMaxSort();
        return ResponseResult.success(maxSort);
    }

    @ApiOperation("获取所有字典详细信息")
    @GetMapping("getAllDictionaryInfo")
    public ResponseResult<List<DictionaryInfoVO>> getAllDictionaryInfo() {
        return ResponseResult.success(sysDictionaryService.getAllDictionaryInfo());
    }

}
