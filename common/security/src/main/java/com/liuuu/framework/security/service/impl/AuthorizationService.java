package com.liuuu.framework.security.service.impl;

import com.liuuu.common.core.util.servlet.ServletUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.framework.security.domain.ApiPermission;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.util.SecurityUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限管理
 *
 * @Author Liuuu
 * @Date 2024/7/25
 */
@Service("auth")
public class AuthorizationService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 当前用户是否有访问当前URL的权限
     * @return
     */
    public boolean hasUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        String requestPath = request.getServletPath();
        LoginUserDetail loginUserDetail = SecurityUtils.getLoginUser();
        if (loginUserDetail.isHasSuperAdmin()) {
            return true;
        }
        return verifyUrlPermission(requestPath, loginUserDetail.getApiPermissions());
    }

    /**
     * 验证是否有权限标识
     * @param permissionCode
     * @return
     */
    public boolean hasPerm(String permissionCode) {
        if (StrUtils.isBlank(permissionCode)) {
            return false;
        }
        LoginUserDetail loginUserDetail = SecurityUtils.getLoginUser();
        if (loginUserDetail.isHasSuperAdmin()) {
            return true;
        }
        for (String targetPermissionCode : loginUserDetail.getPermissionCodes()) {
            if (permissionCode.equals(targetPermissionCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证是否存在某个角色
     * @param roleCode
     * @return
     */
    public boolean hasRole(String roleCode) {
        if (StrUtils.isBlank(roleCode)) {
            return false;
        }
        LoginUserDetail loginUserDetail = SecurityUtils.getLoginUser();
        for (String targetRoleCode : loginUserDetail.getRoleCodes()) {
            if (roleCode.equals(targetRoleCode)) {
                return true;
            }
        }
        return false;
    }

    private boolean verifyUrlPermission(String requestPath, List<ApiPermission> apiPermissionList) {
        if (CollectionUtils.isEmpty(apiPermissionList)) {
            return false;
        }
        HttpServletRequest request = ServletUtils.getRequest();
        for (ApiPermission apiPermission : apiPermissionList) {
            String apiUrl = apiPermission.getApiUrl();
            String apiMethod = apiPermission.getApiMethod();
            if (antPathMatcher.matchStart(apiUrl, requestPath) && request.getMethod().equals(apiMethod)) {
                return true;
            }
        }
        return false;
    }
}
