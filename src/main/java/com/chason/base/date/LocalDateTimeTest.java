package com.chason.base.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeTest {


    public static void main(String[] args) {
        System.out.println("===========test01===========");
        test01();
        System.out.println("===========test02===========");
        test02();
        System.out.println("===========test03===========");
        test03();
        System.out.println("===========test04===========");
        test04();
        System.out.println("===========test05===========");
        test05();
        System.out.println("===========test06===========");
        test06();
        System.out.println("===========test07===========");
        test07();
        System.out.println("===========test08===========");
        test08();
        System.out.println("===========test09===========");
        test09();
    }

    /**
     * 新 api 获取当前时间
     * 输出格式以国际标准格式 ISO 8601为准
     */
    static void test01() {

        LocalDate d = LocalDate.now();
        LocalTime t = LocalTime.now();
        LocalDateTime dt = LocalDateTime.now();

        System.out.println(d);
        System.out.println(t);
        System.out.println(dt);
    }

    /**
     * 三个类的转化
     */
    static void test02() {

        LocalDateTime dt = LocalDateTime.now();
        LocalDate d = dt.toLocalDate();
        LocalTime t = dt.toLocalTime();

        LocalDateTime dt1 = LocalDateTime.of(d, t);

        System.out.println(dt);
        System.out.println(d);
        System.out.println(t);
        System.out.println(dt1);
    }

    /**
     * 通过指定时间的方式创建LocalDateTime
     */
    static void test03() {

        LocalDate d = LocalDate.of(2024, 8, 30);  // 2024-08-30
        LocalTime t = LocalTime.of(15, 13, 10);

        // 如果没有指定 ms 那么构建的时间就没有 ms
        LocalDateTime dt = LocalDateTime.of(2024, 8, 20, 15, 13, 22); // 2024-08-20 15:13:22

        System.out.println(d);
        System.out.println(t);
        System.out.println(dt);
    }

    /**
     * 如果是遵循 ISO 8601格式的时间字符串，可以直接进行转化
     */
    static void test04() {

        LocalDate d = LocalDate.parse("2024-08-30");
        LocalTime t = LocalTime.parse("15:32:13");
        LocalDateTime dt = LocalDateTime.parse("2024-08-30T15:32:13");

        // 这里会报错，DateTimeParseException  因为传统的格式不是ISO 8601标准
        // LocalDateTime dt1 = LocalDateTime.parse("2024-08-30 15:32:13");

        System.out.println(d);
        System.out.println(t);
        System.out.println(dt);
    }

    /**
     * 应用于 LocalDateTime的时间格式化工具  DateTimeFormatter
     */
    static void test05 () {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));

        String sdt = "2024/08/30 15:32:13";

        // 使用自定义格式解析
        LocalDateTime dt = LocalDateTime.parse(sdt, dtf);
        System.out.println(dt);
    }

    /**
     * 对时间的计算
     */
    static void test06() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dtf.format(dt));

        // 时间增加5天 减少2小时
        LocalDateTime dt1 = dt.plusDays(5).minusHours(2);
        System.out.println(dtf.format(dt1));

        // 上面的是对时间的增减，下面的是将指定的时间设置成你的值
        LocalDateTime dt2 = dt.withHour(9);  // set time hour is 9
        System.out.println(dtf.format(dt2));

    }

    /**
     * LocalDateTime 可以做一些更复杂的运算
     */
    static void test07() {

        // 本月第一天  0:00
        LocalDateTime firDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firDay);

        // 本月最后一天
        LocalDate last = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(last);

        // 本月第一个周一
        LocalDate firMon = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firMon);

    }

    /**
     * 判断两个时间的先后
     */
    static void test08() {
        LocalDateTime dt1 = LocalDateTime.of(2024, 8, 30, 12, 30, 30);
        LocalDateTime dt2 = LocalDateTime.of(2024, 7, 30, 12, 30, 30);
        LocalDateTime dt3 = LocalDateTime.of(2024, 8, 30, 12, 40, 30);

        System.out.println(dt1.isBefore(dt2));
        System.out.println(dt1.isBefore(dt3));
        System.out.println(dt2.isAfter(dt1));
        System.out.println(dt2.isAfter(dt3));
    }

    /**
     * Duration 表示两个时间的间隔
     * Period 表示间隔的天数
     */
    static void test09() {

        LocalDateTime dt1 = LocalDateTime.of(2024, 8, 30, 12, 30, 30);
        LocalDateTime dt2 = LocalDateTime.of(2024, 7, 30, 12, 30, 30);

        Duration d = Duration.between(dt1, dt2);
        System.out.println(d);

        Period p = Period.between(dt1.toLocalDate(), dt2.toLocalDate());
        System.out.println(p);
    }

}
