package com.liuuu.common.framework.mybatis.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 分页配置
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
@Getter
@Configuration
public class PageProperty {

    /**
     * 默认分页记录数
     */
    public static Long defaultPageSize;

    /**
     * 最大分页记录数
     */
    public static Long maxPageSize;

    @Value("${page.default-page-size}")
    public void setDefaultPageSize(Long defaultPageSize) {
        PageProperty.defaultPageSize = defaultPageSize;
    }

    @Value("${page.max-page-size}")
    public void setMaxPageSize(Long maxPageSize) {
        PageProperty.maxPageSize = maxPageSize;
    }
}
