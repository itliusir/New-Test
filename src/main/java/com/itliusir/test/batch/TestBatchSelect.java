package com.itliusir.test.batch;

import java.util.Arrays;
import java.util.List;

/**
 * 批量查询
 *
 * @author liugang
 * @since 2018/11/1
 */
public class TestBatchSelect {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3");
        //List<String> list = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11");
        batchSelect(list);
    }

    public static void batchSelect(List<String> ids) {
        int totalCount = ids.size();
        int limitCount = 5;
        List<String> selectList = null;
        int currentCount = totalCount % limitCount == 0
                ? totalCount / limitCount : totalCount / limitCount + 1;
        for (int i = 0; i < currentCount; i++) {
            selectList = ids.subList(i * limitCount,
                    (i + 1) * limitCount > totalCount ? totalCount : (i + 1) * limitCount);
            // 根据selectList 进行 in 查询
            System.out.println(selectList);
        }
    }
}
