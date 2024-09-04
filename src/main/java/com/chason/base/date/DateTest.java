package com.chason.base.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Before JDK 1.8 Java use Date CalendarTest TimeZone to create and calculate time
 *
 */
public class DateTest {

    public static void main(String[] args) {

        dateTest();

    }


    /**
     * try to test date
     * date from java.util.Date
     */
    public static void dateTest() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(now));

        System.out.println("==============");
        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd, yyyy");
        System.out.println(sdf1.format(now));

        Date date = new Date(2019, 12, 31);

        // error : can't do this
        //System.out.println(now - date);

    }



}
