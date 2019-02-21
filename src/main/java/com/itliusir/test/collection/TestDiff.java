package com.itliusir.test.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * List1 list2 different
 *
 * @author liugang
 * @since 2019/2/19
 */

@Slf4j
public class TestDiff {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("a");
        list1.add("b");

        list2.add("b");
        list2.add("a");
        list2.add("c");

        log.info("--->{}", !list2.retainAll(list1));


    }
}
