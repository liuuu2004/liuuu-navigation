package com.liuuu.admin.system.log.operation.config;

import com.liuuu.admin.system.log.operation.mq.LogOperationMq;
import com.liuuu.common.log.constant.LogConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 日志消息中心配置 Redis
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Configuration
public class LogMqConfig {
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
                                                                       MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, new PatternTopic(LogConstant.LOG_MQ_TOPIC));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(LogOperationMq logOperationMq) {
        return new MessageListenerAdapter(logOperationMq, "consumeMqLog");
    }
}
