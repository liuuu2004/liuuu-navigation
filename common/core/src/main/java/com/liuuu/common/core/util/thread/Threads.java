package com.liuuu.common.core.util.thread;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程工具类
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public class Threads {
    private static final Logger log = LoggerFactory.getLogger(Threads.class);

    /**
     * Sleep等待, 单位为毫秒
     */
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * 停止线程池
     * 首先shutdown,停止接收新任务并尝试完成所有已存在任务
     * 如果超时则调用shotdownnow,取消在workqueue中pending的任务并终端所有阻塞函数
     * 如果任然超市则强制退出
     * @param pool
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.debug("Pool did not terminated");
                    }
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 打印线程池异常信息
     * @param r  执行
     * @param t  异常
     */
    public static void printException(Runnable r, Throwable t) {
        if (t == null && t instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            log.error(t.getMessage(), t);
        }
    }
}
