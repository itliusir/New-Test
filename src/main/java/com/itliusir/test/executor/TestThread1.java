package com.itliusir.test.executor;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/12/3
 */

@Slf4j
public class TestThread1 implements Runnable{
    @Override
    public void run() {
        log.info("TestThread1 start ...");
    }
}
