package com.chason._01_base.lesson1;

import java.util.Scanner;

/**
 * 1. Java 注释 func1()
 * 2. 进制  func2()
 * 3. 输入输出 func3()
 */
public class Demo2 {
    public static void main(String[] args) {
        // func1();
        // func2();
        func3();
    }

    private static void func1() {
        int a; // 单行注释

        /* 单行或者多行 */
        int b;

        /*
            也可以多行
            第二行
         */
        int c;

        /**
         * 多行注释，也是文档级注释，可以用来生成文档
         * @Param
         * @Return 等会帮助文档的生成
         */
        int d;
    }

    /**
     * 0b 二进制
     * 0 八进制
     * 0x 十六进制
     */
    private static void func2() {

        int num1 = 0b1001;
        System.out.println("二进制1001:" + num1);  // 1001 = 十进制的 9

        int num2 = 013;
        System.out.println("八进制013：" + num2);

        int num3 = 0x1a;
        System.out.println("十六进制0x1a:" + num3);
    }

    /**
     * 输入输出
     */
    private static void func3() {

        System.out.println("请输入字符串(按enter结束):");
        Scanner sc = new Scanner(System.in);    // 从系统输入
        String msg = sc.next();
        System.out.println("your input string:" + msg);
        System.out.println("请输入整数(按enter结束):");
        int i = sc.nextInt();
        System.out.println("your input integer:" + i);
    }

}
