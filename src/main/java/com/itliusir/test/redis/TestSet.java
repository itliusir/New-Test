package com.itliusir.test.redis;

import com.itliusir.test.redis.base.RedisClusterClient;
import com.itliusir.test.redis.base.RedisFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试redis
 *
 * @author liugang
 * @since 2019/4/19
 */

@Slf4j
public class TestSet {

    protected static RedisClusterClient redisClient = RedisFactory.newRedisCluster();

    private static ConcurrentHashMap<String, AtomicInteger> workerBlockingTask = new ConcurrentHashMap<>();

    static {
        workerBlockingTask.put("559138662", new AtomicInteger(10));
        workerBlockingTask.put("559138663", new AtomicInteger(100000));
        workerBlockingTask.put("559138664", new AtomicInteger(9));
        workerBlockingTask.put("559138665", new AtomicInteger(6));
    }

    public static void main(String[] args) {
        String monitorKey = "worker_blocking_ip_10.32.10.88";
        try {
            for (Map.Entry<String, AtomicInteger> entry: workerBlockingTask.entrySet()) {
                redisClient.hmdel(monitorKey, String.valueOf(entry.getKey()));
                redisClient.hset(monitorKey, String.valueOf(entry.getKey()), String.valueOf(entry.getValue().get()));
                redisClient.expireKey(monitorKey, 60 * 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("sync task blocking monitor error:" + e.getMessage());
        }
        String monitorKey1 = "worker_blocking_ip_10.32.10.89";
        try {
            for (Map.Entry<String, AtomicInteger> entry: workerBlockingTask.entrySet()) {
                redisClient.hmdel(monitorKey1, String.valueOf(entry.getKey()));
                redisClient.hset(monitorKey1, String.valueOf(entry.getKey()), String.valueOf(entry.getValue().get()));
                redisClient.expireKey(monitorKey1, 60 * 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("sync task blocking monitor error:" + e.getMessage());
        }
        String monitorKey2 = "worker_blocking_ip_10.32.10.90";
        try {
            for (Map.Entry<String, AtomicInteger> entry: workerBlockingTask.entrySet()) {
                redisClient.hmdel(monitorKey2, String.valueOf(entry.getKey()));
                redisClient.hset(monitorKey2, String.valueOf(entry.getKey()), String.valueOf(entry.getValue().get()));
                redisClient.expireKey(monitorKey2, 60 * 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("sync task blocking monitor error:" + e.getMessage());
        }
        log.info("sync task blocking monitor success");
    }

}
