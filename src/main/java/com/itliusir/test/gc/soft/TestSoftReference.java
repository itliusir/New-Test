package com.itliusir.test.gc.soft;

import com.itliusir.test.jdk8.lambda.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * test soft reference case
 *
 * @author liugang
 * @since 2019/5/16
 */

@Slf4j
public class TestSoftReference {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<User> referenceQueue = new ReferenceQueue<>();
        SoftReference<User> softReference = new SoftReference<>(new User(1,"索尔",1), referenceQueue);
        //manual gc can not gc soft reference user
        System.gc();
        // sleep 2s
        TimeUnit.SECONDS.sleep(2);
        log.info("manual gc....soft reference:{}",softReference.get());
        log.info("manual gc....queue:{}",referenceQueue.poll());

        //auto gc will oom, can gc soft reference user
        autoGC();
        log.info("auto gc....soft reference:{}",softReference.get());
        log.info("auto gc....queue:{}",referenceQueue.poll());
    }

    private static void autoGC() {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*1024*5]);
        byte[] bytes = new byte[1024*1024*5];
    }
}
