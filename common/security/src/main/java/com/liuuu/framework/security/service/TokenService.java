package com.liuuu.framework.security.service;

import com.liuuu.framework.security.domain.LoginUserDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * token验证处理
 *
 * @Author Liuuu
 * @Date 2024/7/24
 */
public interface TokenService {
    /**
     * 创建登录令牌
     * @param loginUserDetail  登陆用户信息
     * @return
     */
    String createLoginUser(LoginUserDetail loginUserDetail);

    /**
     * 获取登录用户信息
     * @return
     */
    LoginUserDetail getLoginUser();

    /**
     * 获取登录用户信息
     * @param request
     * @return
     */
    LoginUserDetail getLoginUser(HttpServletRequest request);

    /**
     * 删除登录用户
     * @param request
     * @param userId
     * @return
     */
    boolean deleteLoginUser(HttpServletRequest request, Long userId);

    /**
     * 设置刷新权限
     * @param userId  用户id
     * @param token  令牌
     * @param hasRefresh  是否需要刷新
     */
    void setRefreshPermission(Long userId, String token, boolean hasRefresh);

    /**
     * 设置需要刷新权限
     * @param userId
     */
    void setNeedRefreshPermission(Long userId);

    /**
     * 为多个用户设置刷新权限
     * @param userIds
     */
    void setNeedRefreshPermission(List<Long> userIds);

    /**
     * 是否需要刷新权限
     * @param userId
     * @param token
     * @return
     */
    boolean hasRefreshPermission(Long userId, String token);

    /**
     * 设置用户信息到缓存
     * @param token
     * @param loginUserDetail
     * @param expire  过期时间(s)
     */
    void setLoginUserCache(String token, LoginUserDetail loginUserDetail, long expire);

    Long getTokenExpireTime(String token);

    String getToken();

}
