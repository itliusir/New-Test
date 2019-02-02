package com.itliusir.test.jdk8.list;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/12/2
 */
@Slf4j
public class TestListForRemove {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5);
        list.add("A");
        list.add("B");
        list.add("D");
        list.add("D");
        list.add("E");
        remove(list);
        log.info("list:{}", list.toString());

    }

    private static void remove(List<String> list) {

        /*for (int i = 0; i < list.size(); i++) {
            if (Objects.equals("D",list.get(i))){
                list.remove(i);
            }
        }*/
        /*for (String str : list) {
            list.remove(str);
        }*/

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String temp = it.next();
            if (Objects.equals("D",temp)) {
                it.remove();
            }
        }
    }
}
