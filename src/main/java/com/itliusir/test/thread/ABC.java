package com.itliusir.test.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程交替打印 abcabcabc…，一个打印 a，一个打印 b，一个打印 c
 *
 * @author liugang
 * @since 2019/3/6
 */
public class ABC {

    private static Lock lock = new ReentrantLock();
    private static int state = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.getAndIncrement());
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadB extends Thread {

        public ThreadB(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.getAndIncrement());
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadC extends Thread {

        public ThreadC(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {
                        System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.getAndIncrement());
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {


        new ThreadA("Thread0").start();
        new ThreadB("Thread1").start();
        new ThreadC("Thread2").start();
    }

}
