package com.itliusir.test.merge;

import java.util.Arrays;
import java.util.Collections;

/**
 * merge
 *
 * @author liugang
 * @since 2018/9/7
 */
public class TestMergeSort {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 7, 9};
        int[] arr2 = {3, 4, 8};
        System.out.println(Arrays.toString(merge(arr1, arr2)));
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] c = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                c[k++] = arr1[i++];
            } else {
                c[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            c[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            c[k++] = arr2[j++];
        }
        return c;
    }

}
