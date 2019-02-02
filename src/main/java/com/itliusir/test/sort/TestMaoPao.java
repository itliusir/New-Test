package com.itliusir.test.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author liugang
 * @since 2018/12/7
 */
public class TestMaoPao {

    public static void main(String[] args) {
        int[] array = {4, 9, 9, 3, 3, 3, 2, 2, 8, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length -1 -i ; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
