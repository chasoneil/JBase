package com.chason._01_base.lesson1;

/**
 * 参数：
 * 1. 值传递和引用传递 func1()
 * 结论: java都是值传递，如果需要类似交换值的方式，需要借助对象或者数组
 * 2. 关于全局变量的使用 func2()
 */
public class Demo4Test {

    // 用于记录本类被实例化了多少次
    private static int count = 0;

    // 构造方法一旦被调用，count 就加 1
    public Demo4Test() {
        count++;
    }

    public static void main(String[] args) {
        // func1();
        func2();
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

    private static void func2() {
        Demo4Test demo1 = new Demo4Test();
        Demo4Test demo2 = new Demo4Test();
        Demo4Test demo3 = new Demo4Test();
        System.out.println("创建的对象数：" + count);    // 3
    }
}
