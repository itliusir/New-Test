package com.itliusir.test.linked;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/3/28
 */

@Slf4j
public class TestLinkedBlockingDequeTimeOut {

    public static void main(String[] args) throws InterruptedException {
        /*BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(3);

        Thread threadA = new Thread() {
            @Override
            public void run() {
                try {
                    blockingDeque.offer(1,2,TimeUnit.SECONDS);
                    blockingDeque.offer(2,3,TimeUnit.SECONDS);
                    TimeUnit.SECONDS.sleep(5);
                    blockingDeque.offer(3,7,TimeUnit.SECONDS);
                    blockingDeque.offer(4,7,TimeUnit.SECONDS);
                    log.info("poll....{}", blockingDeque.poll());
                    log.info("poll....{}", blockingDeque.poll());
                    log.info("poll....{}", blockingDeque.poll());
                    log.info("poll....{}", Optional.ofNullable(blockingDeque.poll()).orElse(null));
                    log.info("poll....{}", blockingDeque.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        threadA.start();*/

        int i = 1;

        int j = add(i);

        System.out.println(i);

    }

    private static int add(int i) {
        int j = 0;
        i = i + 2;
        j = j + 1;
        return j;
    }
}
