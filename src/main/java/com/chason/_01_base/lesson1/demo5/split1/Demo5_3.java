package com.chason._01_base.lesson1.demo5.split1;

/**
 * 测试访问修饰符的范围  func1()
 * 子类
 */
public class Demo5_3 extends Demo5_1 {

    public static void main(String[] args) {
        func1();
    }

    private static void func1() {
        Demo5_1 fa = new Demo5_1();
        System.out.println(fa.number1);     // default
        System.out.println(fa.number2);     // protected
        System.out.println(fa.number3);     // public

        Demo5_3 son = new Demo5_3();
        System.out.println(son.number1);    // default
        System.out.println(son.number2);    // protected
        System.out.println(son.number3);    // public
    }

}
