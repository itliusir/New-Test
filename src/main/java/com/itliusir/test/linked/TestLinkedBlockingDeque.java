package com.itliusir.test.linked;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 双向并发阻塞队列
 *
 * @author liugang
 * @since 2019/3/15
 */

@Slf4j
public class TestLinkedBlockingDeque {

    /**
     * add : true or error{"Deque full"}
     * offer : true or false
     *
     * take : getOne or await(waiting if necessary until an element becomes available)
     * poll : getOne or getNull
     *
     * @author liugang 2019/3/15 15:04
     * */
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(5);

        Thread threadA = new Thread() {
            @Override
            public void run() {
                log.info("add first to four elements");
                blockingDeque.offer(1);
                blockingDeque.offer(2);
                blockingDeque.offer(3);
                blockingDeque.offer(4);
                try {
                    log.info("poll....{}", blockingDeque.take());
                    log.info("poll....{}", blockingDeque.take());
                    log.info("poll....{}", blockingDeque.take());
                    log.info("poll....{}", blockingDeque.take());
                    log.info("poll....{}", blockingDeque.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                log.info("add the fifth element");
                blockingDeque.offer(5);
            }
        };

        threadA.start();
        TimeUnit.SECONDS.sleep(5);
        threadB.start();

    }
}
