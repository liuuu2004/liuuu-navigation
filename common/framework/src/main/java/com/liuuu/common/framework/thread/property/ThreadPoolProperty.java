package com.liuuu.common.framework.thread.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池相关配置
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "thread")
public class ThreadPoolProperty {

}
