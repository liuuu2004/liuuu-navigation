package com.liuuu.common.swagger.config;

import com.liuuu.common.swagger.property.SwaggerProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Knife4j 接口文档配置
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    private SwaggerProperty swaggerProperty;

    @Bean("defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperty.isEnable())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
        return docket;
    }

    /**
     * 基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperty.getTitle())
                .description(swaggerProperty.getDescription())
                .contact(new Contact(swaggerProperty.getAuthor(), swaggerProperty.getUrl(), swaggerProperty.getEmail()))
                .version(swaggerProperty.getVersion())
                .build();
    }

}
