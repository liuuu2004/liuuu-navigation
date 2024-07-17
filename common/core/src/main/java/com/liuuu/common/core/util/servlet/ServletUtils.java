package com.liuuu.common.core.util.servlet;

import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.web.response.ResponseResult;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import java.io.IOException;

/**
 * 客户端工具类
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public class ServletUtils {

    /**
     * 渲染到客户端
     * @param responseResult
     * @param response
     */
    public static void renderString(ResponseResult responseResult, HttpServletResponse response) {
        renderString(JSON.toJSONString(responseResult), response);
    }

    /**
     * 渲染字符到客户端
     * @param s
     * @param response
     */
    public static void renderString(String s, HttpServletResponse response) {
        response.setStatus(HttpStatus.SUCCESS);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取请求
     * @return
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 请求属性
     * @return
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }
}
