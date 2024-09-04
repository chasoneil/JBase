package com.chason.base.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * Calender before jdk 1.8 used to calculate time
 *
 */
public class CalendarTest {


    public static void main(String[] args) {

        //calendarTest();

        doCalculate();

    }

    private static void printTime(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);

        System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec);
    }

    static void calendarTest() {

        Calendar calendar = Calendar.getInstance();
        printTime(calendar);

        System.out.println("=============");
        Calendar calendar1 = Calendar.getInstance();

        // 月要减一
        calendar1.set(2019, 11, 31, 12, 34, 29);
        printTime(calendar1);

        Date time = calendar.getTime(); // Date <-> Calender 转化
    }

    static void doCalculate() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, 5); // 当前时间增加5分钟
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(calendar.getTime()));

    }
}
