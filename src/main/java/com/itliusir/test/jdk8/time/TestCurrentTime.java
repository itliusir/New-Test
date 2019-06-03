package com.itliusir.test.jdk8.time;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/3/29
 */
public class TestCurrentTime {

    static long beginSecond = System.currentTimeMillis() / 1000;


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 60; i++) {
            long currentSecond = System.currentTimeMillis() / 1000;
            long diffSecond = currentSecond - beginSecond;
            if(diffSecond > 60) {
                break;
            }
            System.out.println("httpClient.execute..." + i);
            TimeUnit.MILLISECONDS.sleep(500);
            long currentEndSecond = System.currentTimeMillis() / 1000;
            long diffEndSecond = currentEndSecond - beginSecond;
            if(diffEndSecond == diffSecond) {
                TimeUnit.SECONDS.sleep(1);
            }
        }
        System.out.println("执行结束");
    }

}
