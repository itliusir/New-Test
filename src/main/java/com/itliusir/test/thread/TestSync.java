package com.itliusir.test.thread;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/4/11
 */
public class TestSync implements Runnable{
    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(500);
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        Thread.sleep(250);
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        long n = 0L;
        for (int i = 0; i < 1000; i++) {
            n = 6541367000L + i;
            findK(n,2,(int)Math.sqrt(n) + 1);
        }
        /*TestSync tt = new TestSync();
        Thread t = new Thread(tt);
        t.start();
        tt.m2();
        System.out.println("main thread b =" + tt.b);*/
    }

    public static void findK(long n,int begin,int end) {
        for (int i = begin; i < end; i++) {
            if (n % i == 0) {
                System.out.println(n + " = " + i + " * " + n/i);
            }
        }
    }


    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
