package com.liuuu.framework.security.service.impl;

import com.liuuu.common.core.util.id.IdUtils;
import com.liuuu.common.core.util.servlet.ServletUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.framework.security.constant.SecurityConstant;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.property.TokenProperty;
import com.liuuu.framework.security.service.TokenService;
import com.liuuu.navigation.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenProperty tokenProperty;

    @Autowired
    private RedisService redisService;

    @Override
    public String createLoginUser(LoginUserDetail loginUserDetail) {
        String token = IdUtils.simpleUUID();
        setLoginUserCache(token, loginUserDetail, tokenProperty.getExpireTime());
        return token;
    }

    @Override
    public LoginUserDetail getLoginUser() {
        return getLoginUser(ServletUtils.getRequest());
    }

    @Override
    public LoginUserDetail getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StrUtils.isBlank(token)) {
            return null;
        }
        String tokenKey = getTokenKey(token);
        Object loginUserObject = redisService.get(tokenKey);
        if (loginUserObject == null) {
            return null;
        }
        return (LoginUserDetail) loginUserObject;
    }

    @Override
    public boolean deleteLoginUser(HttpServletRequest request, Long userId) {
        String token = getToken(request);
        String tokenKey = getTokenKey(token);
        redisService.delete(tokenKey);
        String refreshPermissionKey = getRefreshPermissionKey(userId, token);
        redisService.delete(refreshPermissionKey);
        return true;
    }

    @Override
    public void setRefreshPermission(Long userId, String token, boolean hasRefresh) {
        String refreshKey = getRefreshPermissionKey(userId, token);
        redisService.set(refreshKey, hasRefresh, tokenProperty.getExpireTime(), TimeUnit.SECONDS);
    }

    @Override
    public void setNeedRefreshPermission(Long userId) {
        String refreshKey = getRefreshPermissionKey(userId, "*");
        Collection<String> refreshKeys = redisService.keys(refreshKey);
        for (String key : refreshKeys) {
            redisService.set(key, true, tokenProperty.getExpireTime(), TimeUnit.SECONDS);
        }
    }

    @Override
    public void setNeedRefreshPermission(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }
        for (Long userId : userIds) {
            setNeedRefreshPermission(userId);
        }
    }

    @Override
    public boolean hasRefreshPermission(Long userId, String token) {
        String refreshKey = getRefreshPermissionKey(userId, token);
        Boolean hasRefresh = redisService.get(refreshKey);
        return hasRefresh == null ? false : hasRefresh;
    }

    @Override
    public void setLoginUserCache(String token, LoginUserDetail loginUserDetail, long expire) {
        String tokenKey = getTokenKey(token);
        Date nowDate = new Date();
        loginUserDetail.setLoginTime(nowDate.getTime());
        loginUserDetail.setExpireTime(nowDate.getTime() + expire * 1000);
        redisService.set(tokenKey, loginUserDetail, expire, TimeUnit.SECONDS);
        setRefreshPermission(loginUserDetail.getUserId(), token, false);
    }

    @Override
    public String getToken() {
        return getToken(ServletUtils.getRequest());
    }

    @Override
    public Long getTokenExpireTime(String token) {
        String tokenKey = getTokenKey(token);
        return redisService.getExpireTime(tokenKey);
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenProperty.getHeader());
        if (StrUtils.isBlank(token)) {
            token = request.getParameter(tokenProperty.getTokenParam());
        } else {
            token = token.replace(tokenProperty.getTokenPrefix() + " ", "");
        }
        return token;
    }

    private String getTokenKey(String token) {
        String tokenPrefix = StrUtils.isBlank(tokenProperty.getTokenRedisPrefix())
                ? SecurityConstant.TOKEN_PREFIX : tokenProperty.getTokenRedisPrefix();
        return tokenPrefix + token;
    }

    private String getRefreshPermissionKey(Long userId, String token) {
        String permissionRefreshPrefix = StrUtils.isBlank(tokenProperty.getPermissionRefreshRedisPrefix())
                ? SecurityConstant.PERMISSION_REFRESH_PREFIX : tokenProperty.getPermissionRefreshRedisPrefix();
        return permissionRefreshPrefix + userId + ":" + token;
    }
}
