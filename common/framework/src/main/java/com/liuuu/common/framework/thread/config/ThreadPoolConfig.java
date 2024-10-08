package com.liuuu.common.framework.thread.config;


import com.liuuu.common.core.util.thread.Threads;
import com.liuuu.common.framework.thread.property.ThreadPoolProperty;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程配置
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */

@Configuration
public class ThreadPoolConfig {

    @Autowired
    private ThreadPoolProperty threadPoolProperty;

    /**
     * 线程池任务配置
     * @return
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(threadPoolProperty.getMaxPoolSize());
        executor.setCorePoolSize(threadPoolProperty.getCorePoolSize());
        executor.setQueueCapacity(threadPoolProperty.getQueueCapacity());
        executor.setKeepAliveSeconds(threadPoolProperty.getKeepAliveSeconds());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     * @return
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(threadPoolProperty.getCorePoolSize(),
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }
}
