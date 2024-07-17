package com.liuuu.common.core.util.ip;

import com.liuuu.common.core.util.string.StrUtils;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

/**
 * 地址工具类
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    /**
     * IP地址查询网站
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 未知地址
     */
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        if (StrUtils.isBlank(ip)) {
            return "";
        }
        if (IpUtils.Inter)
    }
}
