package com.liuuu.admin.client.nav.controller;

import com.liuuu.admin.client.nav.service.ClientNavService;
import com.liuuu.admin.client.nav.vo.NavCategoryClientVO;
import com.liuuu.admin.client.nav.vo.NavClientListVO;
import com.liuuu.common.core.web.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端导航
 *
 * @Author Liuuu
 * @Date 2024/8/8
 */
@Api(tags = "客户端导航")
@RestController
@RequestMapping("/client/nav")
public class ClientNavController {
    @Autowired
    private ClientNavService clientNavService;

    @ApiOperation("分类网站搜索")
    @GetMapping("/category/site/list")
    public ResponseResult<NavClientListVO> categorySiteList() {
        NavClientListVO listVO = clientNavService.categorySiteList();
        return ResponseResult.success(listVO);
    }

    @ApiOperation("分类列表")
    @GetMapping("/category/list")
    public ResponseResult<NavClientListVO> categoryList() {
        List<NavCategoryClientVO> list = clientNavService.categoryList();
        return ResponseResult.success(list);
    }
}
