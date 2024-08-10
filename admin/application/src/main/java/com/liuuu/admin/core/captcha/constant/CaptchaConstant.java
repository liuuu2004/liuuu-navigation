package com.liuuu.admin.core.captcha.constant;

/**
 * 验证码常量
 *
 * @Author Liuuu
 * @Date 2024/8/9
 */
public class CaptchaConstant {
    /**
     * 验证码redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha:codes:";

    /**
     * 验证码有效期(min)
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
}
