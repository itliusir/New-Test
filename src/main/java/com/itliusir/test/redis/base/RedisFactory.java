package com.itliusir.test.redis.base;

import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

public class RedisFactory {
    private static ThreadLocal<RedisClient> redisClientThreadLocal  = new ThreadLocal<>();
    private static ThreadLocal<RedisClusterClient> jedisClusterThreadLocal  = new ThreadLocal<>();
    private static final Logger logger = Logger.getLogger(RedisFactory.class);
    private static RedisClient instance;
    private static String redisPath = null;
    private static RedisClusterClient redisClusterClient;

    private static RedisClusterClient redisNoPersistenceClusterClient;

    private static   RedisClient shardClient ;
    private static   RedisClient client ;
    public static void initAllInstance(String path){
        redisPath = path;
    }

    public static RedisClient newInstance() {
        if (instance == null){
            synchronized (RedisFactory.class){
                if (instance == null){
                    try {
                        instance = new RedisClient("redis_1",redisPath);
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
            }
        }
        return instance;
    }

    public static RedisClient newInstance(String name) {
        try {
            if (null == redisClientThreadLocal.get()) {
                synchronized(RedisFactory.class) {
                    if (null == redisClientThreadLocal.get()) {
                        client = new RedisClient(name,redisPath);
                        redisClientThreadLocal.set(client);
                    }
                }
            }
            return redisClientThreadLocal.get();
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    //    public static RedisClient newShardInstance(String name) {
//        try {
//            if (null == client) {
//                synchronized(RedisFactory.class) {
//                    if (null == client) {
//                        client = new RedisClient(name, true,redisPath);
//                    }
//                }
//            }
//            return client;
//        } catch (Exception e) {
//            logger.error(e);
//        }
//        return null;
//    }
//
    public static RedisClusterClient  newRedisCluster() {
        try {
            if (null == redisClusterClient){
                synchronized (RedisFactory.class){
                    if (null == redisClusterClient){
                        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)，默认值是8。
                        int MAX_ACTIVE = 10000;
                        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
                        int MAX_IDLE = 10;
                        // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
                        int MAX_WAIT = 5000;
                        JedisPoolConfig poolConfig = new JedisPoolConfig();
                        poolConfig.setMaxTotal(MAX_ACTIVE);
                        poolConfig.setMaxIdle(MAX_IDLE);
                        poolConfig.setMaxWaitMillis(MAX_WAIT);
                        poolConfig.setTestOnBorrow(true);
                        poolConfig.setTestOnReturn(true);

                        HashSet set = new HashSet<HostAndPort>();

//                        set.add(new HostAndPort("10.32.0.28", 7000));
//                        set.add(new HostAndPort("10.32.0.60", 7000));
//                        set.add(new HostAndPort("10.32.0.4", 7000));
//                        set.add(new HostAndPort("10.32.0.21", 7001));
//                        set.add(new HostAndPort("10.32.0.58", 7001));
//                        set.add(new HostAndPort("10.32.0.24", 7001));

                        set.add(new HostAndPort("10.80.136.146", 8000));
                        set.add(new HostAndPort("10.80.136.146", 8001));
                        set.add(new HostAndPort("10.80.136.146", 8002));
                        set.add(new HostAndPort("10.80.136.146", 8003));
                        set.add(new HostAndPort("10.80.136.146", 8004));
                        set.add(new HostAndPort("10.80.136.146", 8006));

                        /*set.add(new HostAndPort("10.32.0.20", 7000));
                        set.add(new HostAndPort("10.32.0.20", 7000));
                        set.add(new HostAndPort("10.32.0.20", 7001));
                        set.add(new HostAndPort("10.32.0.14", 7000));
                        set.add(new HostAndPort("10.32.0.14", 7001));
                        set.add(new HostAndPort("10.32.0.27", 7000));
                        set.add(new HostAndPort("10.32.0.27", 7001));
                        set.add(new HostAndPort("10.32.0.6", 7000));
                        set.add(new HostAndPort("10.32.0.6", 7001));
                        set.add(new HostAndPort("10.32.0.18", 7000));
                        set.add(new HostAndPort("10.32.0.18", 7001));
                        set.add(new HostAndPort("10.32.0.15", 7000));
                        set.add(new HostAndPort("10.32.0.15", 7001));
                        set.add(new HostAndPort("10.32.0.16", 7000));
                        set.add(new HostAndPort("10.32.0.16", 7001));
                        set.add(new HostAndPort("10.32.0.26", 7000));
                        set.add(new HostAndPort("10.32.0.26", 7001));
                        set.add(new HostAndPort("10.32.0.7", 7000));
                        set.add(new HostAndPort("10.32.0.7", 7001));
                        set.add(new HostAndPort("10.32.0.3", 7000));
                        set.add(new HostAndPort("10.32.0.3", 7001));
                        set.add(new HostAndPort("10.32.0.25", 7000));
                        set.add(new HostAndPort("10.32.0.25", 7001));
                        set.add(new HostAndPort("10.32.0.17", 7000));
                        set.add(new HostAndPort("10.32.0.17", 7001));*/


                        redisClusterClient = new RedisClusterClient(new JedisCluster(set, poolConfig));
                    }
                }
            }
            return redisClusterClient;
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return null;
    }


    public static RedisClusterClient  newPersistenceRedisCluster() {
        try {
            if (null == redisNoPersistenceClusterClient){
                synchronized (RedisFactory.class){
                    if (null == redisNoPersistenceClusterClient){
                        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)，默认值是8。
                        int MAX_ACTIVE = 100;
                        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
                        int MAX_IDLE = 10;
                        // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
                        int MAX_WAIT = 5000;
                        JedisPoolConfig poolConfig = new JedisPoolConfig();
                        poolConfig.setMaxTotal(MAX_ACTIVE);
                        poolConfig.setMaxIdle(MAX_IDLE);
                        poolConfig.setMaxWaitMillis(MAX_WAIT);
                        poolConfig.setTestOnBorrow(true);
                        poolConfig.setTestOnReturn(true);
                        HashSet set = new HashSet<HostAndPort>();

                        set.add(new HostAndPort("10.80.136.146", 8000));
                        set.add(new HostAndPort("10.80.136.146", 8001));
                        set.add(new HostAndPort("10.80.136.146", 8002));
                        set.add(new HostAndPort("10.80.136.146", 8003));
                        set.add(new HostAndPort("10.80.136.146", 8004));
                        set.add(new HostAndPort("10.80.136.146", 8006));

                        /*set.add(new HostAndPort("10.32.0.70", 7000));
                        set.add(new HostAndPort("10.32.0.62", 7000));
                        set.add(new HostAndPort("10.32.0.64", 7000));
                        set.add(new HostAndPort("10.32.0.73", 7001));
                        set.add(new HostAndPort("10.32.0.57", 7001));
                        set.add(new HostAndPort("10.32.0.68", 7001));*/
                        redisNoPersistenceClusterClient = new RedisClusterClient(new JedisCluster(set, poolConfig));
                    }
                }
            }
            return redisNoPersistenceClusterClient;
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }
}
