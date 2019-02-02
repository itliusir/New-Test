package com.itliusir.test.jdk8.list;

import java.util.*;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/11/5
 */
public class TestListRemove {

    /**
     * 1 4 删除
     * 6 7 新增
     *
     * @author liugang 2018/11/5 19:09
     */
    public static void main(String[] args) {
        List<String> oldList = new ArrayList<>();
        oldList.add("1");
        oldList.add("2");
        oldList.add("3");
        oldList.add("4");
        oldList.add("5");
        List<String> newList = new ArrayList<>();
        newList.add("1");
        newList.add("2");
        newList.add("6");
        System.out.println("delete---" + getDifferent(oldList, newList));
        System.out.println("insert---" + getDifferent(newList, oldList));
    }

    private static List<String> getDifferent(List<String> target, List<String> reference) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(target.size());
        for (String string : reference) {
            map.put(string, 1);
        }
        for (String string : target) {
            if (map.get(string) != null) {
                map.put(string, 2);
                continue;
            }
            diff.add(string);
        }
        System.out.println("getDifferent total times " + (System.nanoTime() - st));
        return diff;
    }
}
