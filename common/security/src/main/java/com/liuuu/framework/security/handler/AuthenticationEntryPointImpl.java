package com.liuuu.framework.security.handler;

import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.util.servlet.ServletUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.framework.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败异常处理
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Autowired
    private TokenService tokenService;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        int code = HttpStatus.UNAUTHORIZED;
        String message = MessageUtils.message("security.not.login");
        String token = tokenService.getToken();
        if (StrUtils.isNotBlank(token)) {
            message = MessageUtils.message("security.login.expire");
        }
        ServletUtils.renderString(ResponseResult.fail(code, message), httpServletResponse);
    }
}
