package com.liuuu.admin.nav.category.controller;

import com.liuuu.admin.nav.category.dto.NavCategoryAddDTO;
import com.liuuu.admin.nav.category.dto.NavCategoryListDTO;
import com.liuuu.admin.nav.category.dto.NavCategoryUpdateDTO;
import com.liuuu.admin.nav.category.mapstruct.NavCategoryConverter;
import com.liuuu.admin.nav.category.po.NavCategory;
import com.liuuu.admin.nav.category.service.NavCategoryService;
import com.liuuu.admin.nav.category.vo.NavCategoryTreeVO;
import com.liuuu.admin.nav.category.vo.NavCategoryVO;
import com.liuuu.common.core.web.response.ResponseResult;
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
 * 导航分类
 *
 * @Author Liuuu
 * @Date 2024/8/8
 */
@Api(tags = "导航分类")
@RestController
@RequestMapping("/nav/category")
public class NavCategoryController extends BaseController {
    @Autowired
    private NavCategoryService navCategoryService;

    @ApiOperation("列表")
    @GetMapping("/list")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<List<NavCategoryVO>> list(NavCategoryListDTO listDTO) {
        List<NavCategory> list = navCategoryService.list(listDTO);
        return ResponseResult.success(NavCategoryConverter.INSTANCE.convert(list));
    }

    @ApiOperation("树形")
    @GetMapping("/tree")
    public ResponseResult<List<NavCategoryTreeVO>> tree() {
        List<NavCategoryTreeVO> list = navCategoryService.tree();
        return ResponseResult.success(list);
    }

    @ApiOperation("详情")
    @GetMapping("/getById/{id]")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<NavCategoryVO> getById(@PathVariable Long id) {
        return ResponseResult.success(NavCategoryConverter.INSTANCE.convert(navCategoryService.getById(id)));
    }

    @ApiOperation("新增")
    @PostMapping
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "导航分类", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody NavCategoryAddDTO addDTO) {
        NavCategory navCategory = NavCategoryConverter.INSTANCE.convert(addDTO);
        navCategoryService.save(navCategory);
        return ResponseResult.success();
    }

    @ApiOperation("修改")
    @PutMapping
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "导航分类", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody NavCategoryUpdateDTO updateDTO) {
        navCategoryService.updateById(updateDTO);
        return ResponseResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "导航分类", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long id) {
        navCategoryService.remove(id);
        return ResponseResult.success();
    }

    @ApiOperation("获取最大排序")
    @GetMapping("/getMaxSortByParentId")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSortByParentId(Long parentId) {
        Integer maxSort = navCategoryService.getMaxSortByParentId(parentId);
        return ResponseResult.success(maxSort);
    }
}
