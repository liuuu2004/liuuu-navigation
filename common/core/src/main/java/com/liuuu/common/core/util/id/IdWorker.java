package com.liuuu.common.core.util.id;

public class IdWorker {

    /**
     * 开始时间戳 (2024/7/17)
     */
    private static final long startTime = 1721174400000L;

    /**
     * 机器id所占的位数
     */
    private static final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private static final long dataCenterIdBits = 5L;

    /**
     * 支持的最大机器id
     */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    public static final long getMaxWorkerId() {
        return maxWorkerId;
    }

    /**
     * 支持的最大数据标识id
     */
    private static final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    public  static final long getMaxDataCenterId() {
        return maxDataCenterId;
    }

    /**
     * 序列在id中占用的位数
     */
    private static final long sequenceBits = 12L;

    /**
     * 机器id向左移动12位
     */
    private static final long workerShift = sequenceBits;

    /**
     * 数据标识id向左移动位数
     */
    private static final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳左移位数
     */
    private static final long timeStampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 生成序列的掩码
     */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器id
     */
    private static long workerId;

    /**
     * 数据中心id
     */
    private static long dataCenterId;

    /**
     * 毫秒内序列
     */
    private static long sequence;

    public static long getSequence() {
        return sequence;
    }

    /**
     * 上次生成id的时间戳
     */
    private static long lastTimeStamp = -1L;

    public IdWorker() {};

    /**
     * 构造函数
     *
     * @param workerId  工作id
     * @param dataCenterId  数据中心id
     */
    public IdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        IdWorker.workerId = workerId;
        IdWorker.dataCenterId = dataCenterId;
    }

    /**
     * 线程安全地获取下一个id
     *
     * @return
     */
    public static synchronized long nextId() {
        long timeStamp = timeGen();

        if (timeStamp < lastTimeStamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimeStamp - timeStamp)
            );
        }

        if (lastTimeStamp == timeStamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timeStamp = tilNextMillis(lastTimeStamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimeStamp = timeStamp;

        long id = ((timeStamp - startTime) << timeStampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerShift)
                | sequence;
        return id;
    }

    /**
     * 获取当前时间--毫秒
     *
     * @return  当前时间--毫秒
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒直到获得新的时间戳
     *
     * @param lastTimeStamp  上次生成id的时间戳
     * @return  当前时间戳
     */
    protected static long tilNextMillis(long lastTimeStamp) {
        long timeStamp = timeGen();
        while (timeStamp <= lastTimeStamp) {
            timeStamp = timeGen();
        }
        return timeStamp;
    }

    public static void main(String[] args) {
        System.out.println(IdWorker.nextId());
    }
}
