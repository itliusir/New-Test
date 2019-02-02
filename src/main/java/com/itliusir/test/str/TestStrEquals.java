package com.itliusir.test.str;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/12/2
 */
@Slf4j
public class TestStrEquals {

    public static void main(String[] args) {
        /*String x = "StringTest";
        log.info("x-----adr{}" ,String.valueOf(x.hashCode()));
        String s1 = new StringBuilder().append("String").append("Test").toString();
        log.info("s1.intern()-----adr{}" ,String.valueOf(s1.intern().hashCode()));
        log.info("s1-----adr{}" ,String.valueOf(s1.hashCode()));
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);*/

        Integer i = new Integer(128);
        int i1 = 127;
        System.out.println(i == i1);

    }
}
