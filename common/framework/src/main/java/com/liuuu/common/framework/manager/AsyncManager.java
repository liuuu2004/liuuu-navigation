package com.liuuu.common.framework.manager;

import com.liuuu.common.core.util.spring.SpringUtils;
import com.liuuu.common.core.util.thread.Threads;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步管理器
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
public class AsyncManager {

    /**
     * 操作延迟10ms
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtils.getBean(ScheduledExecutorService.class);

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     * @param task
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 执行任务
     * @param runnable
     */
    public void execute(Runnable runnable) {
        executor.schedule(runnable, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }
}
