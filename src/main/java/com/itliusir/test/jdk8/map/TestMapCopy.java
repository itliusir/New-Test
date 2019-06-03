package com.itliusir.test.jdk8.map;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * map
 *
 * concurrent write
 * concurrent read
 *
 * 1. map data local memory copy
 * 2. read lock & write lock
 *
 * @author liugang
 * @since 2019/5/9
 */

@Slf4j
public class TestMapCopy {

    public  Map<String,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        final Lock r = lock.readLock();
        final Lock w = lock.writeLock();
        TestMapCopy n = new TestMapCopy();
        n.map.put("1","A");
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        // get data
//                        r.lock();
//                        if (n.map.get("1") == null) {
//                            System.out.println("出现null啦");
//                        }
                        System.out.println("key 1 -->" + n.map.get("1"));
                    } finally {
//                        r.unlock();
                    }
                }
            }
        }.start();

        new Thread(){

            @Override
            public void run() {

                while (true) {
                    Map<String,String> map1 = new ConcurrentHashMap<>();
                    try {
                        // update data
//                        w.lock();
                        map1.put("1","B");
//                        n.map = map1;
                        n.map = map1;
                    } finally {
//                        w.unlock();
                    }
//                    m1.clear();
                }
            }
        }.start();
    }
}
