package com.itliusir.test.util.test;

import com.itliusir.test.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/5/20
 */

@Slf4j
public class TestDateUtil {
    public static void main(String[] args) {
        /*Date date = DateUtil.parse(DateUtil.today());
        System.out.println(date);*/
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(2,"2");
        map.put(1,"1");
        map.put(3,"3");

        for (Map.Entry<Integer,String> entry:map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("-----------------------------------------------");
        System.out.println(map.higherKey(3) + " ");
        System.out.println(map.lowerEntry(2).getKey() + " " + map.lowerEntry(2).getValue());
    }
}
