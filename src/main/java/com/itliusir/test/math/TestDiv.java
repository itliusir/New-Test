package com.itliusir.test.math;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/4/25
 */

@Slf4j
public class TestDiv {

    private static Random random = new Random();

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        /*long successCount = 500L;
        long errorCount = 499L;

        long ratio = Math.floorDiv(errorCount,successCount);
        System.out.println(ratio>=1);
        System.out.println(ratio);*/
        /*for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list = new ArrayList<>();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }



        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("list.size= " + list.size());
                    List<String> listTemp = new ArrayList<>();
                    listTemp.add("Temp" + Thread.currentThread().getName());
                    list = listTemp;
                }
            }).start();
        }*/

        /*for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i1 = ThreadLocalRandom.current().nextInt(3);
                    System.out.println(i1);
                }
            }).start();
        }*/




        /*for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // pre
                    *//*TestFuck tf = new TestFuck();
                    tf.setId(Thread.currentThread().getName());
                    List<Integer> list = new ArrayList<>();
                    list.add(1);
                    tf.setProvince(list);*//*

                    //issue
                    List<Integer> temp = list;
                    temp.remove(new Integer(0));
                    temp.sort((o1, o2) -> o1 > o2 ? 1 : -1);
                }
            }).start();
        }*/

        ArrayList myList = new ArrayList();
        String[] myArray;
        try {
            while (true) {
                myList.add("My String");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
