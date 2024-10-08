package com.liuuu.admin.core.security.auth.filter;

import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.service.TokenService;

import com.liuuu.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 令牌认证过滤器,验证令牌有效性
 *
 * @Author Liuuu
 * @Date 2024/8/9
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        LoginUserDetail loginUser = tokenService.getLoginUser(request);
        if (loginUser == null) {
            chain.doFilter(request, response);
            return;
        }

        // 令牌未过期,设置Spring Security
        String token = tokenService.getToken();

        // 是否需要刷新权限?当权限发生变动时,不刷新就能生效
        boolean hasRefreshPermission = tokenService.hasRefreshPermission(loginUser.getUserId(), token);
        if (hasRefreshPermission) {
            // 重新获取用户信息
            loginUser = sysUserService.getLoginUserByUsername(loginUser.getUsername());

            // 设置不需要刷新权限
            tokenService.setRefreshPermission(loginUser.getUserId(), token, false);
        }

        // 如果需要刷新权限或者Authentication为空则重新赋值用户信息
        if (hasRefreshPermission || SecurityUtils.getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }
}
