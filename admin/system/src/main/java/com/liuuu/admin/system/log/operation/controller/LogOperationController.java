package com.liuuu.admin.system.log.operation.controller;

import com.liuuu.admin.system.log.operation.dto.LogOperationPageDTO;
import com.liuuu.admin.system.log.operation.service.LogOperationService;
import com.liuuu.admin.system.log.operation.vo.LogOperationVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Api(tags = "操作日志")
@RestController
@RequestMapping("system/log/operation")
public class LogOperationController extends BaseController {
    @Autowired
    private LogOperationService logOperationService;

    @GetMapping("/page")
    @ApiOperation(("分页列表"))
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<LogOperationVO>> page(LogOperationPageDTO pageDTO) {
        PageVO<LogOperationVO> pageVO = logOperationService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<LogOperationVO> getById(@PathVariable Long id) {
        LogOperationVO logOperationVO = logOperationService.getLogOperationById(id);
        return ResponseResult.success(logOperationVO);
    }
}
