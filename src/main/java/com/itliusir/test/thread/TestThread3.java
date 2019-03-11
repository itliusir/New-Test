package com.itliusir.test.thread;

import java.util.concurrent.Semaphore;

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
public class TestThread3 {
    static volatile int result = 0;
    public static void main(String[] args) throws InterruptedException {
        int N = 3;
        Thread[] threads = new Thread[N];
        final Semaphore[] syncObjects = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            syncObjects[i] = new Semaphore(1);
            if (i != N-1){
                syncObjects[i].acquire();
            }
        }
        for (int i = 0; i < N; i++) {
            final Semaphore lastSemphore = i == 0 ? syncObjects[N - 1] : syncObjects[i - 1];
            final Semaphore curSemphore = syncObjects[i];
            final int index = i;
            threads[i] = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        while (true) {
                            lastSemphore.acquire();
                            System.out.println("thread" + index + ": " + result++);
                            if (result > 100){
                                System.exit(0);
                            }
                            curSemphore.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            threads[i].start();
        }
    }
}
