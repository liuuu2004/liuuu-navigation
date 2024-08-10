package com.liuuu.admin.core.config;

import com.liuuu.common.core.filter.XssFilter;
import com.liuuu.common.core.util.string.StrUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XSS Filter配置
 *
 * @AUthor Liuuu
 * @Date 2024/8/8
 */
@Configuration
public class XssFilterConfig {
    @Value("${xss.excludes}")
    private List<String> excludes;

    @Value("${xss.urlPatterns}")
    private List<String> urlPatterns;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @ConditionalOnProperty(value = "xss.enabled", havingValue = "true")
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(CollectionUtils.isEmpty(urlPatterns) ? new String[]{} : urlPatterns.toArray(new String[urlPatterns.size()]));
        registration.setName("xssFilter");
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", CollectionUtils.isEmpty(excludes) ? "" : StrUtils.join(excludes, ","));
        registration.setInitParameters(initParameters);
        return registration;
    }
}
