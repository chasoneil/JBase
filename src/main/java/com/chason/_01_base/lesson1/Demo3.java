package com.chason._01_base.lesson1;

/**
 * 1. Java 基本数据类型的包装类 以及怎么获取对应类型的最大值和最小值 func1()
 * 2. 每个基本类型的默认值  func2()
 * 3. Java常量 func3()
 * 4. 类型转换 func4()
 * 5. 运算符 func5()
 */
public class Demo3 {

    static byte b;
    static short s;
    static char c;
    static int i;
    static float f;
    static double d;
    static long l;
    static boolean bool;

    public static void main(String[] args) {
        // func1();
        // func2();
        // func3();
        // func4();
        func5();
    }

    private static void func1() {
        // byte
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println();

        // short
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println();

        // int
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println();

        // long
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println();

        // float
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println();

        // double
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println();

        // char
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
        System.out.println("最小值：Character.MIN_VALUE="
                + (int) Character.MIN_VALUE);
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
        System.out.println("最大值：Character.MAX_VALUE="
                + (int) Character.MAX_VALUE);
    }

    private static void func2() {
        System.out.println("byte default:" + b);
        System.out.println("short default:" + s);
        System.out.println("char default:" + c);
        System.out.println("int default:" + i);
        System.out.println("float default:" + f);
        System.out.println("double default:" + d);
        System.out.println("long default:" + l);
        System.out.println("boolean default:" + bool);
    }

    // 常量
    private static void func3() {
        final float PI = 3.14f;
        System.out.println("PI:" + PI);

        // PI = 3.1415f; error 常量不能改！
    }

    /**
     * 数据类型转换
     * 规则： 小转大可以  大转小需要强制转化
     * 所有的类型只针对变量，常量则不受控制
     */
    private static void func4() {

        /* 小转大 不会丢失精度 */
        float f1 = 34.89f;
        double d1 = f1;
        System.out.println("d1:" + d1);  // todo: d1:34.88999938964844

        short s1 = 1;
        int i1 = s1;
        System.out.println("i1:" + i1);

        byte b1 = 3;
        short s2 = b1;
        System.out.println("s2:" + s2);

        /* 大转小 可能丢失精度 */
        int num1 = 4;
        // short num2 = num1; 编译直接报错
        short num2 = (short) num1;
        System.out.println("num2:" + num2);

        double num3 = 32.123256322;
        float num4 = (float) num3;
        System.out.println("num4:" + num4);  // num4:32.123257

        float number1 = 10.25f;
        int number2 = (int) number1;
        System.out.println("number2:" + number2);

        /* 常量不受这个规范控制 */
        short sh1 = 20 + 30;        // 整形常量依然能够给short类型
        System.out.println("sh1:" + sh1);
    }

    /**
     * 运算符相关的小练习
     */
    private static void func5() {
        int n = 5;
        n = ++n + ++n;
        System.out.println("n:" + n);

        int m = 3;
        m = ++m + m++;
        System.out.println("m:" + m);
    }
}
