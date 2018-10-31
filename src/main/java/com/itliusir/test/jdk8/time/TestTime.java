package com.itliusir.test.jdk8.time;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JDK8的时间处理类
 *
 * @author liugang
 * @since 2018-07-25
 */
public class TestTime {

    public static void main(String[] args) throws ParseException {

        // 获取当前日期 2018-07-25
        /*LocalDate ld = LocalDate.now();
        System.out.println(ld);

        //获取年月日
        int year = ld.getYear();
        int month = ld.getMonthValue();
        int day = ld.getDayOfMonth();
        System.out.println(year + "-" + month + "-" + day);
        System.out.println(LocalDate.of(year,month,day));

        //具体到时间
        LocalDateTime lct = LocalDateTime.now();
        System.out.println(lct);*/

        String time = "26/6/2018";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(sdf.parse(time));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        System.out.println(LocalDate.parse(time,formatter));
        /*String time1 = "2018-6-26";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDate.parse(time1,formatter1));*/


//        String timeArray[] = time.split("/");
//        LocalDate timeFormatter = LocalDate.of(Integer.valueOf(timeArray[2]),Integer.valueOf(timeArray[1]),Integer.valueOf(timeArray[0]));
//        System.out.println(timeFormatter);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //
//        System.out.println(l.format(formatter));
        //

        /*LocalDate day = LocalDate.parse(time,formatter);
        System.out.println(day.toString());*/

    }
}
