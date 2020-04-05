package com.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @date:2020/2/20 14:14
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class ExecutorUtil {


    public static long executorTask(Runnable runnable, int count, int core) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(core);
        long start = System.currentTimeMillis();
        IntStream.range(0, count).forEach(value -> pool.execute(runnable));
        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        return System.currentTimeMillis() - start;
    }
}
