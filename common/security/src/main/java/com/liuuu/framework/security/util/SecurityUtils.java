package com.liuuu.framework.security.util;

import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.exception.ServiceException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.framework.security.domain.LoginUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全工具类
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
public class SecurityUtils {
    /**
     * 获取用户id
     * @return
     */
    public static Long getUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取登录用户
     * @return
     */
    public static LoginUserDetail getLoginUser() {
        try {
            return (LoginUserDetail) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, MessageUtils.message("user.info.fail"));
        }
    }

    /**
     * 获取Authentication
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder加密密码
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * 判断加密密码是否相同
     * @param rawPassword  真实密码
     * @param encodedPassword  加密后字符
     * @return
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
