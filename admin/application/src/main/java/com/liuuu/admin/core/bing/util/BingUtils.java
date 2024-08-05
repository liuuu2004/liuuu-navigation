package com.liuuu.admin.core.bing.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuuu.common.core.util.http.HttpUtils;

/**
 * 微软Bing工具类
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
public class BingUtils {
    private static final String BING_URL = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN";

    /**
     * 获取Bing主页图片
     * @return
     */
    public static String getBingOneImage() {
        String resultStr = HttpUtils.sentGet(BING_URL, null);
        JSONObject jsonObject = JSON.parseObject(resultStr);
        JSONObject imageObject = jsonObject.getJSONArray("images").getJSONObject(0);
        return "https://cn.bing.com" + imageObject.getString("url");
    }
}
