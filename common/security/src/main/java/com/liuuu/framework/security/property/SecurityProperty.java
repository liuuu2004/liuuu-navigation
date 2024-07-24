package com.liuuu.framework.security.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 权限配置
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
@Component
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperty {
    /**
     * 不登录就能访问的地址
     */
    private String[] notLoginUrls;

}
