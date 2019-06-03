package com.itliusir.test.date;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/5/13
 */

@Slf4j
public class TestDate {
    public static void main(String[] args) {
        /*int now = Integer.valueOf(new SimpleDateFormat("HHmm").format(new Date()));
        log.info("now--->{}", now);*/
        /*int tem = Math.round(12/5.0f);
        log.info("tem--{}",tem);*/
        int tpv = 0;
        int tmp = 1;
        int threeNumberBufferMapValue = 29;

        int bufferTpv = Math.round(tmp / 4f + tmp / 4f * 3f / 30f * threeNumberBufferMapValue) ;
        System.out.println(bufferTpv);
    }
}
