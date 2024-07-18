package com.liuuu.common.core.util.ip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuuu.common.core.util.http.HttpUtils;
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

    /**
     * 根据IP获取用户地址
     * @param ip  IP地址
     * @return
     */
    public static String getRealAddressByIP(String ip) {
        if (StrUtils.isBlank(ip)) {
            return "";
        }

        if (IPUtils.internalIP(ip)) {
            return "Internal IP";
        }

        try {
            String response = HttpUtils.sendGet(IP_URL, "ip=" + ip.split(",")[0] + "&json=true", "GBK");
            if (StrUtils.isBlank(response)) {
                return UNKNOWN;
            }
            JSONObject jsonObject = JSON.parseObject(response);
            String region = jsonObject.getString("pro");
            String city = jsonObject.getString("city");
            return String.format("%s %s", region, city);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }
}
