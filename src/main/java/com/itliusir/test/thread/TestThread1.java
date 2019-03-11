package com.itliusir.test.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过N个线程顺序循环打印从0至100，如给定N=3则输出：
 * thread0: 0
 * thread1: 1
 * thread2: 2
 * thread0: 3
 * thread1: 4
 *
 * @author liugang
 * @since 2019/3/8
 */
public class TestThread1 {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static int state = 1;

    public static void main(String[] args) {
        testPrint(4);
    }

    private static void testPrint(int n) {
        /*ThreadFactory threadNameFactory = new ThreadFactoryBuilder().setNameFormat("Thread%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(n, n,
                1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000),
                threadNameFactory, new ThreadPoolExecutor.AbortPolicy());*/
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        while (true) {
            if (atomicInteger.get() > 100) {
                break;
            }
            executorService.execute(
                    () -> {
                        if (atomicInteger.get()>100) {
                            return;
                        }
                        String name = Thread.currentThread().getName();
                        int j = Integer.parseInt(StringUtils.substring(name, name.length() - 1, name.length()));
                        if (j == state) {
                            System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.getAndIncrement());
                            if (state == n) {
                                state = 1;
                            } else {
                                state ++;
                            }
                        }

                    }
            );
        }

        executorService.shutdown();

    }
}
