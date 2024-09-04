package com.chason.base.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * before jdk1.8 we use timezone to reset timezone
 *
 */
public class TimeZoneTest {

    public static void main(String[] args) {

        timezoneTest();

        transferTimezone();

    }

    static void timezoneTest() {
        TimeZone defaultZone = TimeZone.getDefault();
        System.out.println("default:" + defaultZone.getID());

        TimeZone gmt9 = TimeZone.getTimeZone("GMT+9:00");
        System.out.println("gmt9:" + gmt9.getID());

        TimeZone shanghai = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println("Shanghai:" + shanghai.getID());
        System.out.println("============================");
    }

    /**
     * transfer timezone by calendar and simpleDateFormat
     */
    static void transferTimezone() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println(sdf.format(calendar.getTime()));
    }

}
