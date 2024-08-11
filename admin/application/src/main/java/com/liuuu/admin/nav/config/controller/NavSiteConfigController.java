package com.liuuu.admin.nav.config.controller;

import com.liuuu.admin.nav.config.dto.NavSiteConfigUpdateDTO;
import com.liuuu.admin.nav.config.service.NavSiteConfigService;
import com.liuuu.admin.nav.config.vo.NavSiteConfigAboutVO;
import com.liuuu.admin.nav.config.vo.NavSiteConfigVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 网站配置
 *
 * @Author Liuuu
 * @Date 2024/8/11
 */
@Api(tags = "网站配置")
@RestController
@RequestMapping("/nav/config")
public class NavSiteConfigController {
    @Autowired
    private NavSiteConfigService navSiteConfigService;

    @ApiOperation("获取配置")
    @GetMapping("/getConfig")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<NavSiteConfigVO> getConfig() {
        NavSiteConfigVO navSiteConfigVO = navSiteConfigService.getConfig();
        return ResponseResult.success(navSiteConfigVO);
    }

    @ApiOperation("修改")
    @PutMapping
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "网站配置", businessType = BusinessType.UPDATE)
    public ResponseResult<Long> update(@Valid @RequestBody NavSiteConfigUpdateDTO updateDTO) {
        navSiteConfigService.updateById(updateDTO);
        return ResponseResult.success(updateDTO.getId());
    }

    @ApiOperation("关于本站")
    @GetMapping("/about")
    public ResponseResult<NavSiteConfigAboutVO> about() {
        NavSiteConfigAboutVO navSiteConfigAboutVO = navSiteConfigService.about();
        return ResponseResult.success(navSiteConfigAboutVO);
    }
}
