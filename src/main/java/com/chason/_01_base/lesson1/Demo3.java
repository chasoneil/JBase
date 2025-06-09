package com.chason._01_base.lesson1;

/**
 * 1. Java 基本数据类型的包装类 以及怎么获取对应类型的最大值和最小值 func1()
 * 2. 每个基本类型的默认值  func2()
 * 3. Java常量 func3()
 * 4. 类型转换 func4()
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
        func4();
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
     * 规则： 小转大可以  浮点转整形会丢失精度，直接省略小数位
     */
    private static void func4() {

        float f1 = 34.89f;
        int i1 = (int) f1;
        System.out.println("i1:" + i1);

        f1= -32.12f;
        i1 = (int) f1;
        System.out.println("i1:" + i1);

        int i2 = 3;
        float f2 = i2; // 小转大
        System.out.println("f2:" + f2);  // 3.0
    }
}
