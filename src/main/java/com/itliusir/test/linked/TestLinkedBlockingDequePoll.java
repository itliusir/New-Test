package com.itliusir.test.linked;

import com.itliusir.test.juc.CopyOnWriteArrayMap;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * poll test
 *
 * @author liugang
 * @since 2019/4/1
 */

public class TestLinkedBlockingDequePoll {

    private static ConcurrentHashMap<String, ConcurrentHashMap<String, BlockingDeque<Integer>>> groupQueues = new ConcurrentHashMap<>();

    private final static ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final static Lock r = rwl.readLock();
    private final static Lock w = rwl.writeLock();

    public static void main(String[] args) {
        /*BlockingDeque<Integer> type0Deque = new LinkedBlockingDeque<>(5);
        BlockingDeque<Integer> type1Deque = new LinkedBlockingDeque<>(5);
        BlockingDeque<Integer> type2Deque = new LinkedBlockingDeque<>(5);*/


        new Thread(new Runnable() {
            @Override
            public void run() {
                w.lock();
                try {
                    for (int i = 0; i < 10; i++) {
                        int j = (int) (Math.random() * 3);
                        String group = "group" + i;
                        String type = "type" + j;
                        // 业务逻辑
                        if (!groupQueues.containsKey(group)) {
                            groupQueues.put(group, new ConcurrentHashMap<>());
                        }
                        ConcurrentHashMap<String, BlockingDeque<Integer>> queues = groupQueues.get(group);
                        if (!queues.containsKey(type)) {
                            queues.put(type, new LinkedBlockingDeque<>());
                        }
                        queues.get(type).offer(i);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    w.unlock();
                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                r.lock();
                try {
                    while (true) {
                        groupQueues.forEach((k, v) -> v.forEach((vK, vV) -> System.out.println(vV.peek())));
                    }
                } finally {
                    r.unlock();
                }
            }
        }).start();


    }
}
