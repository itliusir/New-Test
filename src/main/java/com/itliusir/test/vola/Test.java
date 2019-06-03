package com.itliusir.test.vola;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/5/14
 */

@Slf4j
public class Test {
    int result;

    public int getResult() {
        return result;
    }

    public synchronized void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) {
        Test threadSafeCache = new Test();

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;
                    sync();
                }
                /*boolean flag = threadSafeCache.getResult() < 100; // set之前 true
                while (flag) {
                    x++;
                }*/
                System.out.println(x);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadSafeCache.setResult(200);
    }

    public synchronized static void sync() {

    }
}
