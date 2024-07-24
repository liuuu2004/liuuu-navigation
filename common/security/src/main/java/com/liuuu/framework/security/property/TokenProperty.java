package com.liuuu.framework.security.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * token配置
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenProperty {
    /**
     * 自定义令牌标识
     */
    private String header;

    private long expireTime;

    private String tokenParam;

    private String tokenPrefix;

    private String tokenRedisPrefix;

    /**
     * 刷新权限Redis前缀
     */
    private String permissionRefreshRedisPrefix;
}
