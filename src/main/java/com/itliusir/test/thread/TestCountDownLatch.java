package com.itliusir.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 3 个任务同时启动
 *
 * @author liugang
 * @since 2019/3/9
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread1 = new Thread(new MyThread(countDownLatch));
        Thread thread2 = new Thread(new MyThread(countDownLatch));
        Thread thread3 = new Thread(new MyThread(countDownLatch));

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("already start.....");

        TimeUnit.SECONDS.sleep(2);

        countDownLatch.countDown();
    }


    static class MyThread implements Runnable {

        private CountDownLatch countDownLatch;

        public MyThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("start.....................");
        }
    }
}
