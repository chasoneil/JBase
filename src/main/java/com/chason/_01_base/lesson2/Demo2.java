package com.chason._01_base.lesson2;

/**
 * Java Character and String
 * 1. Character常用的方法    func1()
 * 2. String的内存分配       func2()
 */
public class Demo2 {

    public static void main(String[] args) {
        // func1();
        func2();
    }

    /* Character的常见用法 */
    private static void func1() {
        Character ch = 'c';
        Character ch1 = new Character('c');

        // 对象和字符本身不是一个东西
        System.out.println(ch == ch1);      // false

        System.out.println("isLetter:" + Character.isLetter(ch));
        System.out.println("isDigit:" + Character.isDigit(ch));
        System.out.println("isWhitespace:"+ Character.isWhitespace(ch));
        System.out.println("isUpperCase:" + Character.isUpperCase(ch));
    }

    // String
    private static void func2() {

        String s1 = "name";
        String s2 = "name";
        String s3 = s1;

        String s4 = new String("name");
        String s5 = new String("name");

        System.out.println(s1 == s2);   // true 指向公共池的同一个字符串
        System.out.println(s2 == s3);

        System.out.println(s1 == s4);   // false 不是同一个内存了，第一个是公共池，第二个是堆
        System.out.println(s4 == s5);   // false 两个不同的对象

        System.out.println("----------------------------");

        String e1 = "";
        String e2 = " ";        // false 空格也不是空
        String e3 = "haha";
        String e4 = null;
        System.out.println(e1.isEmpty());
        System.out.println(e2.isEmpty());
        System.out.println(e3.isEmpty());
        // System.out.println(e4.isEmpty()); 会抛出空指针异常
    }

}
