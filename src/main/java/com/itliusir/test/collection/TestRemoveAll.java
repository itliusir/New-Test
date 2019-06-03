package com.itliusir.test.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/5/16
 */

@Slf4j
public class TestRemoveAll {

    private static List<Integer> listOne = new ArrayList<>();
    private static List<Integer> listTwo = new ArrayList<>();

    static {
        listOne.add(1);
        listOne.add(2);
        listOne.add(3);
        listOne.add(4);
        listOne.add(5);
        listOne.add(6);
        listOne.add(7);
        listOne.add(8);
        listOne.add(9);
        listOne.add(10);

        listTwo.add(2);
        listTwo.add(3);
    }

    public static void main(String[] args) {
        listOne.removeAll(listTwo);

        System.out.println(listOne);
    }
}
