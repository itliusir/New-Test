package com.itliusir.test.juc.test;

import com.itliusir.test.juc.CopyOnWriteArrayMap;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/5/16
 */

@Slf4j
public class TestCopyOnWriteMap {

    private static CopyOnWriteArrayMap<String,String> map = new CopyOnWriteArrayMap<>();

    static {
        map.put("1","2");
    }

    public static void main(String[] args) {
        System.out.println(map.get("1"));
    }

}
