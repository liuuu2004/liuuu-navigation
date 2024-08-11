package com.liuuu.admin.core.security.auth.controller;

import com.liuuu.admin.core.security.auth.dto.AuthLoginDTO;
import com.liuuu.admin.core.security.auth.service.AuthService;
import com.liuuu.admin.core.security.auth.vo.AuthLoginVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 认证中心
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@RestController
@Api(tags = "认证中心")
public class AuthController extends BaseController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResponseResult<AuthLoginVO> login(@Valid @RequestBody AuthLoginDTO loginDTO) {
        AuthLoginVO authLoginVO = authService.login(loginDTO);
        return ResponseResult.success(authLoginVO);
    }
}
