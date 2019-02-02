package com.itliusir.test.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
 *
 * @author liugang
 * @since 2018/10/13
 */
@Slf4j
public class Test1 {

    public static void main(String[] args) {
        /*boolean isFalse = false ||true ||false;
        System.out.println(isFalse);*/
        /*int[] A = {3, 1, 2, 4,5,6};
        System.out.println(Arrays.toString(solution(A)));*/
        List list = Arrays.asList("1","2");
        log.info("list:{}",list);
    }

    private static int[] solution(int[] A) {
        int x = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                int temp = A[x];
                A[x] = A[i];
                A[i] = temp;
                x++;
            }
        }
        return A;
    }
}
