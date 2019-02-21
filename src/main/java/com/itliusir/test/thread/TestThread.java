package com.itliusir.test.thread;

/**
 * 多线程
 *
 * @author liugang
 * @since 2018-08-21
 */
public class TestThread {

    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        /*Thread1 t1 = new Thread1("thread-1");
        Thread2 t2 = new Thread2("thread-2");
        new Thread(t1).start();
        new Thread(t2).start();*/

        new Thread() {
            @Override
            public void run() {
                System.out.println("xxx");
                new TestThread().add();
            }
        }.start();

        Thread.sleep(1000);

        new TestThread().add();
        System.out.println(i);
    }

    public void add() {
        synchronized (this) {
            i++;
        }
    }


}

/*
class Thread1 implements Runnable {
    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        User.name = "/tmp/usr".substring(4);
    }
}

class Thread2 implements Runnable {
    private String name;

    public Thread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String str = User.name;
        if (str.equals("/tmp")) {
            System.out.println(str);
        }
        System.out.println(str);
    }
}

class User {
    public static String name = "/tmp";
}
*/
