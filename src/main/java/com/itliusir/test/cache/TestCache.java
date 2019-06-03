package com.itliusir.test.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.itliusir.test.array.Test2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/4/2
 */

@Slf4j
public class TestCache {

    public static Cache<String,List<String>> CACHE = CacheBuilder.newBuilder().recordStats().expireAfterWrite(3, TimeUnit.SECONDS).build();

    /*public static void main(String[] args) throws InterruptedException {
        String x = "111";
        List<String> list = new ArrayList<>();
        list.add(x);
        CACHE.put("key",list);
        TimeUnit.SECONDS.sleep(2);
        TimeUnit.SECONDS.sleep(2);
        CACHE.getIfPresent("key").forEach(log::info);
    }*/

    public static void main(String[] args) {
        /*String res = "{\n" +
                "  \"code\": 100,\n" +
                "  \"left_ip\": 299996,\n" +
                "  \"left_time\": 2001134,\n" +
                "  \"number\": 1,\n" +
                "  \"domain\": \"183.129.207.81\",\n" +
                "  \"port\": [\n" +
                "    15758\n" +
                "  ]\n" +
                "}";
        JSONObject arr = JSON.parseObject(res);
        Integer code = arr.getInteger("code");
        System.out.println(code);
        String proxyIp = arr.getString("domain");
        System.out.println(proxyIp);
        JSONArray ports = arr.getJSONArray("port");
        for(int i = 0;i< ports.size();i++){
            int port = ports.getInteger(i);
            System.out.println(port);
        }*/
        System.out.println(StringUtils.isNotEmpty(null));
    }



}
