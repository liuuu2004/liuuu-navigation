package com.liuuu.common.core.util.id;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class IdWorkerTest {

    private static final int THREAD_COUNT = 100;
    private static final int ITERATIONS_PER_THREAD = 1000;

    @Test
    public void testConcurrentNextId() throws InterruptedException, ExecutionException {
        final IdWorker idWorker = new IdWorker(1, 1);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        Set<Long> idSet = new HashSet<>();
        Callable<Void> task = () -> {
            for (int i = 0; i < ITERATIONS_PER_THREAD; i++) {
                long id = idWorker.nextId();
                synchronized (idSet) {
                    assertFalse("Duplicate Id generated", idSet.contains(id));
                    idSet.add(id);
                }
            }
            return null;
        };
        Future<?>[] futures = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = executorService.submit(task);
        }
        for (Future<?> future : futures) {
            future.get();
        }
        executorService.shutdown();
        assertEquals("Total unique IDs", THREAD_COUNT * ITERATIONS_PER_THREAD, idSet.size());
    }

//    @Test(expected = RuntimeException.class)
//    public void testClockBackwards() throws NoSuchFieldException, IllegalAccessException {
//        final IdWorker idWorker = new IdWorker(1, 1);
//
//        Field lastTimeStampField = IdWorker.class.getDeclaredField("lastTimeStamp");
//        lastTimeStampField.setAccessible(true);
//        lastTimeStampField.set(null, System.currentTimeMillis() + 10000);
//        idWorker.nextId();
//    }

    @Test
    public void testMaxValues() {
        final IdWorker idWorker = new IdWorker(IdWorker.getMaxWorkerId(), IdWorker.getMaxDataCenterId());

        for (int i = 0; i < IdWorker.getSequence(); i++) {
            long id = idWorker.nextId();
            assertTrue("ID should be greater than 0", id > 0);
        }
    }

    @Test(timeout = 1000)
    public void testPerformance() {
        final IdWorker idWorker = new IdWorker(1, 1);

        for (int i = 0; i < 100000; i++) {
            long id = idWorker.nextId();
            assertTrue("ID should be greater than 0", id > 0);
        }
    }

}
