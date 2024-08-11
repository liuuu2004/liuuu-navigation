package com.liuuu.admin.nav.site.controller;

import com.liuuu.admin.nav.site.dto.NavSiteAddDTO;
import com.liuuu.admin.nav.site.dto.NavSitePageDTO;
import com.liuuu.admin.nav.site.dto.NavSiteUpdateDTO;
import com.liuuu.admin.nav.site.mapstruct.NavSiteConverter;
import com.liuuu.admin.nav.site.service.NavSiteService;
import com.liuuu.admin.nav.site.vo.NavSiteVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.enums.BusinessType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 导航网站
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Api(tags = "导航网站")
@RestController
@RequestMapping("/nav/site")
public class NavSiteController {
    @Autowired
    private NavSiteService navSiteService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<NavSiteVO>> page(NavSitePageDTO pageDTO) {
        PageVO<NavSiteVO> pageVO = navSiteService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("详情")
    @GetMapping("/getById/{id}")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<NavSiteVO> getById(@PathVariable Long id) {
        return ResponseResult.success(NavSiteConverter.INSTANCE.convert(navSiteService.getById(id)));
    }

    @ApiOperation("新增")
    @PostMapping
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "导航网站", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody NavSiteAddDTO addDTO) {
        navSiteService.save(addDTO);
        return ResponseResult.success();
    }

    @ApiOperation("修改")
    @PutMapping
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "导航网站", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody NavSiteUpdateDTO updateDTO) {
        navSiteService.updateById(updateDTO);
        return ResponseResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "导航网站", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        navSiteService.removeByIds(ids);
        return ResponseResult.success();
    }

    @ApiOperation("获取最大排序")
    @GetMapping("/getMaxSortByCategoryId")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSortByCategoryId(Long categoryId) {
        Integer maxSort = navSiteService.getMaxSortByCategoryId(categoryId);
        return ResponseResult.success(maxSort);
    }

    @ApiOperation("更新点击量")
    @PutMapping("/updateClickCount/{id}")
    public ResponseResult updateClickCount(@PathVariable Long id) {
        navSiteService.updateClickCountById(id);
        return ResponseResult.success();
    }

}
