package com.itliusir.test.vola;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author liugang
 * @since 2018-08-06
 */
public class TestAtomic {

    private static AtomicInteger test = new AtomicInteger();



    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        Thread timer = new Thread(new Test1Task());
        timer.setName("test-task");
        timer.start();
    }

    private static void test2() {
        Thread timer = new Thread(new Test2Task());
        timer.setName("test-task");
        timer.start();
    }

    static class Test1Task implements Runnable {

        @Override
        public void run() {
            System.out.println(test.get());
        }
    }

    static class Test2Task implements Runnable {

        @Override
        public void run() {
            test.incrementAndGet();
            test.getAndIncrement();
        }
    }
}
