package com.liuuu.framework.security.handler;

import com.liuuu.common.core.util.servlet.ServletUtils;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理
 *
 * @Author Liuuu
 * @Date 2024/7/24
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoginUserDetail loginUserDetail = tokenService.getLoginUser(httpServletRequest);
        if (loginUserDetail != null) {
            tokenService.deleteLoginUser(httpServletRequest, loginUserDetail.getUserId());
        }
        ServletUtils.renderString(ResponseResult.success(), httpServletResponse);
    }
}
