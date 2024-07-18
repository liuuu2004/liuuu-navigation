//package com.liuuu.common.core.util.thread;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//public class ThreadsTest {
//
//    private ExecutorService executorService;
//
//    @Before
//    public void setUp() {
//        executorService = Executors.newFixedThreadPool(2);
//    }
//
//    @After
//    public void tearDown() {
//        Threads.shutdownAndAwaitTermination(executorService);
//    }
//
//    @Test
//    public void testShutdownAndAwaitTermination() throws InterruptedException {
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//
//        Future<?> future = pool.submit(() -> {
//            Threads.sleep(500);
//        });
//
//        Threads.shutdownAndAwaitTermination(pool);
//
//        assertTrue(pool.isShutdown());
//        assertFalse(pool.isTerminated());
//
//        try {
//            future.get();
//        } catch (ExecutionException e) {
//            fail("Task should have completed normally");
//        }
//
//        assertTrue(pool.isTerminated());
//    }
//
//}
