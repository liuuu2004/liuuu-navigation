package com.liuuu.admin.core.security.auth.service.impl;

import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.common.core.exception.ServiceException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.framework.security.domain.LoginUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取登录用户
        LoginUserDetail loginUserDetail = sysUserService.getLoginUserByUsername(username);
        if (loginUserDetail == null) {
            throw new ServiceException(MessageUtils.message("login.username.password.not.match"));
        }
        return loginUserDetail;
    }
}
