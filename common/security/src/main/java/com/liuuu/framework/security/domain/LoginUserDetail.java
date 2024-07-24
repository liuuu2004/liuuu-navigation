package com.liuuu.framework.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 登陆用户身份
 *
 * @Author Liuuu
 * @Date 2024/7/24
 */
@Data
public class LoginUserDetail implements UserDetails {
    private Long userId;

    private String username;

    private String password;

    private String token;

    private long loginTime;

    private long expireTime;

    /**
     * 权限列表
     */
    private Set<String> permissionCodes = new HashSet<>();

    /**
     * 接口权限列表
     */
    private List<ApiPermission> apiPermissions = new ArrayList<>();

    /**
     * 角色编码
     */
    private Set<String> roleCodes = new HashSet<>();

    private boolean hasSuperAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
