package com.itliusir.test.expire;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Test Ivt Expire
 *
 * @author liugang
 * @since 2019/4/11
 */

@Slf4j
public class TestIvt {

    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).maximumSize(1000).build();
        String taskId = "";
        try {
            for (int i = 0; i < 10000; i++) {
                taskId = String.valueOf(i);
                String result = cache.getIfPresent(taskId);
                cache.put(taskId,"1");
                if (Objects.isNull(cache.getIfPresent(taskId))) {
                    // 。。。
                }
            }
        } finally {

        }

    }
}
