package com.liuuu.admin.system.log.login.controller;

import com.liuuu.admin.system.log.login.dto.LogLoginAddDTO;
import com.liuuu.admin.system.log.login.dto.LogLoginPageDTO;
import com.liuuu.admin.system.log.login.mapstruct.LogLoginConverter;
import com.liuuu.admin.system.log.login.po.LogLogin;
import com.liuuu.admin.system.log.login.service.LogLoginService;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Api(tags = "登录日志")
@RestController
@RequestMapping("/system/log/login")
public class LogLoginController extends BaseController {
    @Autowired
    private LogLoginService logLoginService;

    @GetMapping("/page")
    @ApiOperation(("分页列表"))
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<LogLoginAddDTO>> page(LogLoginPageDTO pageDTO) {
        PageVO<LogLogin> pageVO = logLoginService.page(pageDTO);
        return ResponseResult.success(LogLoginConverter.INSTANCE.converter(pageVO));
    }
}
