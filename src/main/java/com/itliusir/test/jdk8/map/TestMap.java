package com.itliusir.test.jdk8.map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/11/5
 */
public class TestMap {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        /*System.out.println(tableSizeFor(15));*/
        List<String> strings = Arrays.asList("111","2222");
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(strings));
        System.out.println(array);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
