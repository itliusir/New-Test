package com.itliusir.test.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/12/3
 */

@Slf4j
public class TestThreadFactory implements ThreadFactory {

    private final AtomicInteger i = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        log.info("new Thread ...");
        return new Thread(r, "test-Thread-" + i.incrementAndGet());
    }
}
