package com.liuuu.framework.security.domain;

import lombok.Data;

/**
 * 接口权限
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
@Data
public class ApiPermission {
    /**
     * 接口地址
     */
    private String apiUrl;

    /**
     * 接口请求方式
     */
    private String apiMethod;
}
