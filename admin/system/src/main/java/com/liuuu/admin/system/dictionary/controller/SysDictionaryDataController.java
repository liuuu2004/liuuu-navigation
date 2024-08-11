package com.liuuu.admin.system.dictionary.controller;

import com.liuuu.admin.system.dictionary.dto.SysDictionaryDataAddDTO;
import com.liuuu.admin.system.dictionary.dto.SysDictionaryDataPageDTO;
import com.liuuu.admin.system.dictionary.dto.SysDictionaryDataUpdateDTO;
import com.liuuu.admin.system.dictionary.dto.SysDictionaryPageDTO;
import com.liuuu.admin.system.dictionary.mapstruct.SysDictionaryDataConverter;
import com.liuuu.admin.system.dictionary.po.SysDictionaryData;
import com.liuuu.admin.system.dictionary.service.SysDictionaryDataService;
import com.liuuu.admin.system.dictionary.vo.SysDictionaryDataVO;
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
import java.util.Arrays;

/**
 * 系统字典数据
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Api(tags = "系统字典数据")
@RestController
@RequestMapping("/system/dictionary/data")
public class SysDictionaryDataController extends BaseController {
    @Autowired
    private SysDictionaryDataService sysDictionaryDataService;

    @ApiOperation("分类列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysDictionaryDataVO>> page(SysDictionaryDataPageDTO pageDTO) {
        pageDTO.setOrderColumn("sort,id");
        PageVO<SysDictionaryData> pageVO = sysDictionaryDataService.page(pageDTO);
        return ResponseResult.success(SysDictionaryDataConverter.INSTANCE.convert(pageVO));
    }

    @ApiOperation("详情")
    @GetMapping("/getById/{id}")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysDictionaryDataVO> getById(@PathVariable Long id) {
        return ResponseResult.success(SysDictionaryDataConverter.INSTANCE.convert(sysDictionaryDataService.getById(id)));
    }

    @ApiOperation("新增")
    @PostMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统字典数据", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody SysDictionaryDataAddDTO addDTO) {
        SysDictionaryData sysDictionaryData = SysDictionaryDataConverter.INSTANCE.convert(addDTO);
        sysDictionaryDataService.save(sysDictionaryData);
        return ResponseResult.success();
    }

    @ApiOperation("修改")
    @PutMapping()
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统字典数据", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody SysDictionaryDataUpdateDTO updateDTO) {
        SysDictionaryData sysDictionaryData = SysDictionaryDataConverter.INSTANCE.convert(updateDTO);
        sysDictionaryDataService.updateById(sysDictionaryData);
        return ResponseResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "系统字典数据", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysDictionaryDataService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

    @ApiOperation("获取最大排序")
    @GetMapping("/getMaxSortByDictionaryId")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSortByDictionaryId(Long dictionaryId) {
        Integer maxSort = sysDictionaryDataService.getMaxSortByDictionaryId(dictionaryId);
        return ResponseResult.success(maxSort);
    }

}
