package com.itliusir.test.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * in:
 * 0 0 0 1 2 1 2 1 2 1
 * out:
 * 0 1 1 0 1 1 0 1 1 1
 *
 * @author liugang
 * @since 2019/3/26
 */

@Slf4j
public class TestShuffle {


    public static void main(String[] args) {

        Item item1 = new Item(1, 0, "type1");
        Item item2 = new Item(2, 0, "type1");
        Item item3 = new Item(3, 0, "type1");
        Item item4 = new Item(4, 1, "type2");
        Item item5 = new Item(5, 2, "type2");
        Item item6 = new Item(4, 1, "type2");
        Item item7 = new Item(5, 2, "type3");
        Item item8 = new Item(4, 1, "type3");
        Item item9 = new Item(6, 3, "type3");

        List<Item> list = new ArrayList<>();

        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        list.add(item8);
        list.add(item9);

        /*for (int i = 0; i < 25471; i++) {
            list.add( new Item(i, 0, "type"));
        }

        for (int i = 25471; i < 55796; i++) {
            list.add( new Item(447178, 1, "type"));
        }*/


        //Collections.shuffle(list);
        long time1 = System.currentTimeMillis();
        List<Item> list2 = list.stream().filter(item -> item.getCount() > 0).collect(Collectors.toList());

        //Collections.shuffle(list2);
        List<Item> list1 = list.stream().filter(item -> item.getCount() == 0).collect(Collectors.toList());
        List<Item> newList = new ArrayList<>(list.size());
        if (list2.size() >= list1.size()) {
            shuffle(list, list2, list1, newList);
        } else {
            shuffle(list, list1, list2, newList);
        }


        newList.forEach(item -> System.out.println(item.getId() + " " + item.getCount() + " " + item.getType()));
        log.info("total times:{}", System.currentTimeMillis() - time1);
        /*Collections.sort(list, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return Math.random() * (i1.getCount() + Math.random()) > Math.random() * (i2.getCount() + Math.random()) ? -1 : 1;
            }
        });

        log.info("random sort:{}", list);*/
        /*List list = Arrays.asList(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3);
        *//*Collections.shuffle(list);
        log.info("shuffle:{}", list);*//*

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.random() * o1 > Math.random() * o2 ? -1 : 1;
            }
        });

        log.info("random sort:{}", list);*/
    }

    private static void shuffle(List<Item> list, List<Item> list2, List<Item> list1, List<Item> newList) {
        int i = 1, j = 0;
        int temp = Math.floorDiv(list.size(), list1.size());
        for (Item item : list2) {
            newList.add(item);
            if (++i == temp && j < list1.size()) {
                newList.add(list1.get(j));
                i = 1;
                j++;
            }

        }
    }

}
