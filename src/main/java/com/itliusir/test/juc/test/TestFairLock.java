package com.itliusir.test.juc.test;

import com.itliusir.test.juc.ReentrantFairLock;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/4/29
 */

@Slf4j
public class TestFairLock {

    private static final Lock FAIR_LOCK = new ReentrantFairLock();
    private static Condition condition = FAIR_LOCK.newCondition();

    public static void main(String[] args) throws Exception {

        final Thread thread1 = new Thread("Thread 1") {
            @Override
            public void run() {
                FAIR_LOCK.lock();
                log.info("{} 正在运行 ...",Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                    log.info("{} 停止运行，等待一个 signal",Thread.currentThread().getName());
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{} 获取到一个 signal, 继续运行",Thread.currentThread().getName());
                FAIR_LOCK.unlock();
            }
        };

        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        final Thread thread2 = new Thread("Thread 2") {
            @Override
            public void run() {
                FAIR_LOCK.lock();
                log.info("{} 正在运行 ...",Thread.currentThread().getName());
                thread1.interrupt();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signal();
                log.info("{} 发送一个 signal",Thread.currentThread().getName());
                FAIR_LOCK.unlock();
            }
        };

        thread2.start();

    }

}
