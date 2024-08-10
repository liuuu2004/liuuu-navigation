package com.liuuu.common.core.filter;

import com.liuuu.common.core.util.string.StrUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 防止XSS攻击的过滤器
 *
 * @Author Liuuu
 * @Date 2024/8/8
 */
public class XssFilter implements Filter {
    /**
     * 排除链接
     */
    public List<String> excludes = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String tempExcludes = filterConfig.getInitParameter("excludes");
        if (StrUtils.isNotEmpty(tempExcludes)) {
            String[] url = tempExcludes.split(",");
            for (int i = 0; url != null && i < url.length; ++i) {
                excludes.add(url[i]);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (handleExcludeURL(request, response)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(xssRequest, servletResponse);
    }

    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getServletPath();
        String method = request.getMethod();
        // GET && DELETE 不过滤
        if (method == null || HttpMethod.GET.matches(method) || HttpMethod.DELETE.matches(method)) {
            return true;
        }
        return StrUtils.matches(url, excludes);
    }

    @Override
    public void destroy() {

    }
}
