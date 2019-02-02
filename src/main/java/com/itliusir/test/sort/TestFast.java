package com.itliusir.test.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author liugang
 * @since 2018/12/7
 */
public class TestFast {

    public static void main(String[] args) {
        int[] array = {4, 9, 9, 3, 3, 3, 2, 2, 8, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        if (array.length > 0) {
            sort(array, 0, array.length - 1);
        }
    }

    private static void sort(int[] array, int low, int high) {
        if (low < high) {
            int middle = getMiddle(array, low, high);
            sort(array, low, middle - 1);
            sort(array, middle + 1, high);
        }
    }

    private static int getMiddle(int[] array, int low, int high) {
        int temp = array[low];

        while (low < high) {
            while (low < high && array[high] >= temp) {
                high--;
            }
            array[low] = array[high];

            while (low < high && array[low] < temp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }
}
