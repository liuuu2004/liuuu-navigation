package com.liuuu.admin.system.user.service.impl;

import com.liuuu.admin.system.user.mapstruct.SysUserConverter;
import com.liuuu.admin.system.user.po.SysUser;
import com.liuuu.admin.system.user.service.SysUserInfoService;
import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.service.TokenService;
import com.liuuu.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public SysUserVO getUserInfo() {
        LoginUserDetail loginUser = SecurityUtils.getLoginUser();

        SysUser sysUser = sysUserService.getById(loginUser.getUserId());
        loginUser = SysUserConverter.INSTANCE.convertDetail(sysUser);
        sysUserService.setLoginUserPermission(loginUser);

        String token = tokenService.getToken();
        Long expireTime = tokenService.getTokenExpireTime(token);
        tokenService.setLoginUserCache(token, loginUser, expireTime);

        SysUserVO sysUserVO = SysUserConverter.INSTANCE.convert(sysUser);
        sysUserVO.setPassword("");
        sysUserVO.setPermissionCodes(loginUser.getPermissionCodes());
        sysUserVO.setRoleNames(loginUser.getRoleCodes());

        return sysUserVO;
    }
}
