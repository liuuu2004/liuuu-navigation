package com.liuuu.admin.core.security.auth.service.impl;

import com.liuuu.admin.core.captcha.constant.CaptchaConstant;
import com.liuuu.admin.core.security.auth.dto.AuthLoginDTO;
import com.liuuu.admin.core.security.auth.service.AuthService;
import com.liuuu.admin.core.security.auth.vo.AuthLoginVO;
import com.liuuu.admin.system.log.login.enums.LogLoginStatus;
import com.liuuu.admin.system.log.login.factory.LogLoginAsyncFactory;
import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.common.core.exception.ServiceException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.framework.manager.AsyncManager;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.service.TokenService;
import com.liuuu.navigation.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 认证中心
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisService redisService;

    @Override
    public AuthLoginVO login(AuthLoginDTO loginDTO) {
        // 校验登录验证码
        verifyLoginCode(loginDTO);

        Authentication authentication = null;

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                String message = MessageUtils.message("login.username.password.not.match");

                // 异步记录登录日志
                AsyncManager.me().execute(LogLoginAsyncFactory.add(username, LogLoginStatus.FAIL.code, message));
                throw new ServiceException(message);
            } else if (e instanceof InternalAuthenticationServiceException) {
                String message = e.getMessage();

                // 异步记录登录日志
                AsyncManager.me().execute(LogLoginAsyncFactory.add(username, LogLoginStatus.FAIL.code, message));
                throw new ServiceException(message);
            } else {
                String message = e.getMessage();

                // 异步记录登录日志
                AsyncManager.me().execute(LogLoginAsyncFactory.add(username, LogLoginStatus.FAIL.code, message));
                throw new ServiceException(message);
            }
        }
        // 获取用户
        LoginUserDetail loginUserDetail = (LoginUserDetail) authentication.getPrincipal();
        // 设置用户权限
        sysUserService.setLoginUserPermission(loginUserDetail);
        // 创建令牌
        String token = tokenService.createLoginUser(loginUserDetail);
        AuthLoginVO authLoginVO = new AuthLoginVO();
        authLoginVO.setToken(token);

        // 异步记录日志方法
        AsyncManager.me().execute(LogLoginAsyncFactory.add(loginUserDetail.getUserId(), username,
                LogLoginStatus.SUCCESS.code, MessageUtils.message("login.success")));
        return authLoginVO;
    }

    private void verifyLoginCode(AuthLoginDTO loginDTO) {
        String verifyKey = CaptchaConstant.CAPTCHA_CODE_KEY + loginDTO.getUuid();
        String captcha = redisService.get(verifyKey);
        redisService.delete(verifyKey);
        if (captcha == null) {
            String message = MessageUtils.message("login.captcha.expire");
            AsyncManager.me().execute(LogLoginAsyncFactory.add(
                    loginDTO.getUsername(), LogLoginStatus.FAIL.code, message
            ));
            throw new ServiceException(message);
        }
        if (!loginDTO.getCode().equalsIgnoreCase(captcha)) {
            String message = MessageUtils.message("login.captcha.error");
            AsyncManager.me().execute(LogLoginAsyncFactory.add(
                    loginDTO.getUsername(), LogLoginStatus.FAIL.code, message
            ));
            throw new ServiceException(message);
        }
    }
}
