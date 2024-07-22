package com.liuuu.common.framework.config;

import com.liuuu.common.framework.file.property.FileUploadProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源配置
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Autowired
    private FileUploadProperty fileUploadProperty;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUploadProperty.getMapPrefix() + "/**")
                .addResourceLocations("file:" + fileUploadProperty.getBasePath() + "/");
    }
}
