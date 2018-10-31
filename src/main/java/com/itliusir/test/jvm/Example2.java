package com.itliusir.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author liugang
 * @since 2018-05-17
 */
public class Example2 {

    public static void main(String[] args) {
        foo();
    }

    public static void foo(){
        List<String> list = new ArrayList<>(16);
        list.add("A");
        list.add("B");
        list.forEach( s -> { System.out.println(s); } );
    }
}
