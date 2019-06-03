package com.itliusir.test.array;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/4/2
 */

@Slf4j
public class Test2 {

    public static ConcurrentHashMap<String, List<String>> getTaskDependentMap() {
        return taskDependentMap;
    }

    private static ConcurrentHashMap<String, List<String>> taskDependentMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String x = "sasdsad5a45wqe6qw{}''''...,klo///kiio;uq9uwq09jl;cxma'-0wuqe98qw7e0-qik;ax,mclxjfq97e09";
                List<String> list = new ArrayList<>();
                //list.add(x);
                Test2.getTaskDependentMap().put("a", list);
                for (int i = 0; i < 10; i++) {
                    Test2.getTaskDependentMap().get("a").remove(x);
                    Test2.getTaskDependentMap().get("a").add(x);
                }
            }
        }) {}.start();

        while (true) {
            if (Test2.getTaskDependentMap().containsKey("a")) {
                List<String> x = Test2.getTaskDependentMap().get("a");
                x.forEach(log::info);
                break;
            }
        }
    }
}
