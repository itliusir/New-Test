package com.itliusir.test.topk;

import java.util.ArrayList;
import java.util.List;

/**
 * TOP-K问题
 *
 * @author liugang
 * @since 2018/9/7
 */
public class Test {

    public static void main(String[] args) {
        int[] array = {4, 9, 9, 3, 3, 3, 2, 2, 8, 8};
        System.out.println(findTopK(array, 4));
    }

    /**
     * 给一个乱序很多重复数的数组，取第k大的数
     *
     * @author liugang 2018/9/7 14:22
     */
    private static Integer findTopK(int[] array, int k) {
        int max = array[0];

        for (int arr : array) {
            if (max < arr) {
                max = arr;
            }
        }

        int[] count = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            count[array[i]] += 1;
        }

        List<Integer> topKList = new ArrayList<>();
        for (int sumCount = 0, j = count.length - 1; j >= 0; j--) {
            if (count[j] != 0) {
                topKList.add(j);
                sumCount++;
            }
            if (sumCount >= k) {
                return topKList.get(sumCount - 1);
            }
        }
        return 0;

    }


}
