package com.chason.base.lesson1;

/**
 * 参数：
 * 1. 值传递和引用传递 func1()
 */
public class Demo4_1 {

    public static void main(String[] args) {
        func1();
    }

    /**
     * 基本数据类型：值传递
     * 引用数据类型: 引用传递
     * java 所有的参数都是引用传递，包括基本数据类型和引用数据类型
     * 引用类型传递的是引用的副本指向原来的对象
     */
    private static void func1() {

        // 基本数据类型
        int a = 5, b = 10;
        System.out.println("before: a=" + a + " b=" + b);   // 5 10
        swap1(a, b);
        System.out.println("after: a=" + a + " b=" + b);    // 5 10

        // 引用数据类型
        String str1 = "Hello", str2 = "Chason";
        System.out.println("before: str1=" + str1 + " str2=" + str2);   // Hello Chason
        swap2(str1, str2);
        System.out.println("after: str1=" + str1 + " str2=" + str2);    // Hello Chason
    }

    private static void swap1(int a, int b) {
        if (a == b) {
            return;
        }
        int temp = a;
        a = b;
        b = temp;
        System.out.println("swap1: a=" + a + " b=" + b);    // 10 5
    }

    private static void swap2(String str1, String str2) {
        String tmp = "";
        tmp = str1;
        str1 = str2;
        str2 = tmp;
        System.out.println("swap2: str1=" + str1 + " str2=" + str2);    // Chason Hello
    }




}
