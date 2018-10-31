package com.itliusir.test.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/10/24
 */
public class TestObjHashCode {
    public static void main(String[] args) {
        User user = new User();

        user.setId("a");
        List<String> list = new ArrayList<>();
        list.add("bcNew");
        list.add("bc");
        user.setList(list);
        List<String> list1 = new ArrayList<>();
        list1.add("d");
        Map<String,List<String>> map = new HashMap<>();
        map.put("d",list1);
        user.setMap(map);
        System.out.println(user.hashCode());

        User userNew = new User();
        userNew.setId("a");
        List<String> listNew = new ArrayList<>();
        listNew.add("bc");
        listNew.add("bcNew");
        userNew.setList(listNew);
        List<String> list1New = new ArrayList<>();
        list1New.add("d");
        Map<String,List<String>> mapNew = new HashMap<>();
        mapNew.put("d",list1New);
        userNew.setMap(mapNew);
        System.out.println(userNew.hashCode());

    }
}
