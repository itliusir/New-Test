package com.itliusir.test.lock;

import com.itliusir.test.thread.TestThread;
import lombok.extern.slf4j.Slf4j;

/**
 * test case
 *
 * @author liugang
 * @since 2019/2/21
 */

@Slf4j
public class TestSynchronized {
    /**
     * case1
     * 锁方法，new多个对象，是否互斥 ----->不互斥
     * case2
     * 锁对象/变量，new多个对象，是否互斥 ----->互斥
     * case3
     * 锁Class，new多个对象，是否互斥 -----> 互斥
     * 
     * */
    /*private TestSynchronized pub1 = new TestSynchronized();
    private TestSynchronized pub2 = new TestSynchronized();*/
    public static void main(String[] args) throws InterruptedException {
        TestSynchronized pri1 = new TestSynchronized();
        TestSynchronized pri2 = new TestSynchronized();
        new Thread() {
            @Override
            public void run() {
                try {
                    pri1.case4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    pri2.case4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public synchronized void case1() throws InterruptedException {
        log.info("我是case1");
        Thread.sleep(5000);
        log.info("case1 stop");
    }

    private static String lock = "";
    public void case2() throws InterruptedException {
        synchronized (lock) {
            log.info("我是case2");
            Thread.sleep(5000);
        }
    }

    public void case3() throws InterruptedException {
        synchronized (TestSynchronized.class) {
            log.info("我是case3");
            Thread.sleep(5000);
        }
    }

    public void case4() throws InterruptedException {
        log.info(Thread.currentThread() + "-------------------1");
        log.info(Thread.currentThread() + "-------------------2");
        log.info(Thread.currentThread() + "-------------------3");
        log.info(Thread.currentThread() + "-------------------4");
        log.info(Thread.currentThread() + "-------------------5");
        log.info(Thread.currentThread() + "-------------------6");
        log.info(Thread.currentThread() + "-------------------7");
        log.info(Thread.currentThread() + "-------------------8");
        log.info(Thread.currentThread() + "-------------------9");
        log.info(Thread.currentThread() + "-------------------10");
        log.info(Thread.currentThread() + "-------------------11");
        log.info(Thread.currentThread() + "-------------------12");
        log.info(Thread.currentThread() + "-------------------13");
        log.info(Thread.currentThread() + "-------------------14");
        log.info(Thread.currentThread() + "-------------------15");


    }
}
