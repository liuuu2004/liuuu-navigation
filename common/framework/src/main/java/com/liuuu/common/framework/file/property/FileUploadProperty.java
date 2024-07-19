package com.liuuu.common.framework.file.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */

@Configuration
@Data
@ConfigurationProperties(prefix = "file-upload")
public class FileUploadProperty {

    private MultipartProperties multipartProperties;

    private Long nameMaxLength = 200L;

    private Long maxSize;

    private String basePath;

    private String mapPrefix;

    private String domain;

    public String getFileImageBasePath() {
        return basePath + "/image";
    }

    public long getFileMaxSize() {
        return multipartProperties.getMaxFileSize().toBytes();
    }

}
