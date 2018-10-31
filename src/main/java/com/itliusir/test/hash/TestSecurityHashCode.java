package com.itliusir.test.hash;

import java.util.*;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/10/24
 */
public class TestSecurityHashCode {

    public static void main(String[] args) {
        SecurityDataitem securityDataitem = new SecurityDataitem();
        securityDataitem.setClassname("class");
        securityDataitem.setMethodname("post");
        securityDataitem.setNote("notes");
        Set<String> set = new LinkedHashSet<>();
        set.add("set");
        securityDataitem.setOriginalPath(set);
        securityDataitem.setType("1");
        List<SecurityDataitem> securityDataitems = new ArrayList<>();
        securityDataitems.add(securityDataitem);

        SecurityData securityData = new SecurityData();
        List<String> list = new ArrayList<>();
        list.add("list");
        Map<String, List<String>> map = new HashMap<>();
        map.put("map",list);
        securityData.setFullPath(map);
        securityData.setServiceId("service-A");
        securityData.setSecurityDataitems(securityDataitems);

        System.out.println(securityData.hashCode());

        SecurityDataitem securityDataitemNew = new SecurityDataitem();
        securityDataitemNew.setClassname("class");
        securityDataitemNew.setMethodname("post");
        securityDataitemNew.setNote("notes");
        Set<String> setNew = new LinkedHashSet<>();
        setNew.add("set");
        securityDataitemNew.setOriginalPath(setNew);
        securityDataitemNew.setType("1");
        List<SecurityDataitem> securityDataitemsNew = new ArrayList<>();
        securityDataitemsNew.add(securityDataitemNew);

        SecurityData securityDataNew = new SecurityData();
        List<String> listNew = new ArrayList<>();
        listNew.add("list");
        Map<String, List<String>> mapNew = new HashMap<>();
        mapNew.put("map",listNew);
        securityDataNew.setFullPath(mapNew);
        securityDataNew.setServiceId("service-A");
        securityDataNew.setSecurityDataitems(securityDataitemsNew);

        System.out.println(securityDataNew.hashCode());
    }
}
