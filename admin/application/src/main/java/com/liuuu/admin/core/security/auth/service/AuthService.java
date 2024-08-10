package com.liuuu.admin.core.security.auth.service;

import com.liuuu.admin.core.security.auth.dto.AuthLoginDTO;
import com.liuuu.admin.core.security.auth.vo.AuthLoginVO;

/**
 * 认证中心
 *
 * @Authoe Liuuu
 * @Date 2024/8/9
 */
public interface AuthService {
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    AuthLoginVO login(AuthLoginDTO loginDTO);
}
