package com.liuuu.admin.nav.index.controller;

import com.liuuu.admin.nav.index.service.NavIndexService;
import com.liuuu.admin.nav.index.vo.NavIndexStatisticsVO;
import com.liuuu.common.core.web.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台首页展示
 *
 * @Author Liuuu
 * @Date 2024/8/11
 */
@Api(tags = "后台首页")
@RestController
@RequestMapping("/nav/index")
public class NavIndexController {
    @Autowired
    private NavIndexService navIndexService;

    @ApiOperation("获取统计数")
    @GetMapping("/getStatistics")
    public ResponseResult<NavIndexStatisticsVO> getStatistics() {
        NavIndexStatisticsVO statisticsVO = navIndexService.getStatistics();
        return ResponseResult.success(statisticsVO);
    }
}
