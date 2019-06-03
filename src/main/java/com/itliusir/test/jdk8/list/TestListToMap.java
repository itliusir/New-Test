package com.itliusir.test.jdk8.list;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * List -> Map
 *
 * @author liugang
 * @since 2019/5/9
 */

@Slf4j
public class TestListToMap {

    private static List<TaskDependentInfo> data = new ArrayList<>();
    private static ConcurrentHashMap<String, List<String>> taskDependentInfoMap = new ConcurrentHashMap<>();


    static {
        TaskDependentInfo t1 = new TaskDependentInfo("aaa","A","user1");
        TaskDependentInfo t2 = new TaskDependentInfo("bbb","A","user2");
        TaskDependentInfo t3 = new TaskDependentInfo("ccc","B","user3");
        TaskDependentInfo t4 = new TaskDependentInfo("ddd","B","user1");
        TaskDependentInfo t5 = new TaskDependentInfo("eee","C","user2");
        TaskDependentInfo t6 = new TaskDependentInfo("fff","C","user3");

        data.add(t1);
        data.add(t2);
        data.add(t3);
        data.add(t4);
        data.add(t5);
        data.add(t6);

    }

    public static void main(String[] args) {
        Map<String, List<TaskDependentInfo>> collect = data.stream().collect(Collectors.groupingBy(TaskDependentInfo::getDependentType));
        collect.forEach(
                (s, taskDependentInfos) -> {
                    List<String> dependentDataList = taskDependentInfos.stream().map(TaskDependentInfo::getDependentData).collect(Collectors.toList());
                    taskDependentInfoMap.put(s,dependentDataList);
                }
        );

        System.out.println(taskDependentInfoMap);
    }







    static class TaskDependentInfo {

        private String id;

        private String dependentType;

        private String dependentData;

        public TaskDependentInfo() {
        }

        public TaskDependentInfo(String id, String dependentType, String dependentData) {
            this.id = id;
            this.dependentType = dependentType;
            this.dependentData = dependentData;
        }

        public String getDependentData() {
            return dependentData;
        }

        public void setDependentData(String dependentData) {
            this.dependentData = dependentData;
        }

        public String getDependentType() {

            return dependentType;
        }

        public void setDependentType(String dependentType) {
            this.dependentType = dependentType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "TaskDependentInfo{" +
                    "id='" + id + '\'' +
                    ", dependentType='" + dependentType + '\'' +
                    ", dependentData='" + dependentData + '\'' +
                    '}';
        }
    }
}
