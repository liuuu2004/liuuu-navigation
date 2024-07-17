package com.liuuu.common.core.util.id;

import java.util.UUID;

/**
 * ID 生成器
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public class IdUtils {
    public static String simpleUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
