package com.chason._01_base.lesson2;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Java Number & Math
 *  包装类 Integer Float Double Long Short Byte 都是继承自Number
 *  1. Number 中提供的 xxxValue() 方法   func1()
 *  2. int 怎么和 double 比较 func2()
 *  3. 大数处理  BigInteger BigDecimal fun3()
 *  4. Math中的常用方法  func4()
 *
 */
public class Demo1 {

    public static void main(String[] args) {
        // func1();
        // func2();
        // func3();
        func4();
    }

    /**
     * Number 提供的最重要的方法 xxxValue()
     */
    private static void func1() {
        Number n1 = 10.23;

        System.out.println("int:" + n1.intValue());
        System.out.println("short:" + n1.shortValue());
        System.out.println("float:" + n1.floatValue());
        System.out.println("long:" + n1.longValue());
        System.out.println("double:" + n1.doubleValue());
        System.out.println("byte:" + n1.byteValue());         // JDK8
    }

    private static void func2() {

        int x = 4;
        double y = 4.00;
        System.out.println(x == y);

        Integer x1 = new Integer(x);
        Double y1 = new Double(y);
        // System.out.println(x1 == y1); 不能这么判断，因为两个都是对象
        System.out.println(x1.doubleValue() == y1.doubleValue());
    }

    /**
     * 处理大数 BigInteger BigDecimal
     */
    private static void func3() {
        BigInteger bigInt = new BigInteger("12345678901234567");       // 使用字符串传递
        bigInt = bigInt.add(new BigInteger("1"));
        System.out.println("bigInt:" + bigInt);

        BigDecimal bigDec = new BigDecimal("1234567.01234567");
        bigDec = bigDec.multiply(new BigDecimal("2"));
        System.out.println("bigDec:" + bigDec);
    }

    /**
     * Math 中的常见方法
     */
    private static void func4() {
        // 三角函数
        System.out.println("sin90:" + Math.sin(Math.PI / 2));
        // 常量
        System.out.println("PI:" + Math.PI);

        // 高级运算
        System.out.println("sqrt:" + Math.sqrt(4)); // 开方
        System.out.println("pow:" + Math.pow(2, 2));        // 第一个数的第二个数次方
        System.out.println("log:" + Math.log(Math.E));
        System.out.println("log10:" + Math.log10(100));

        // 随机数
        System.out.println("random:" + Math.random());
    }

}
