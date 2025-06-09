package com.chason._01_base.lesson1.demo5.split2;

import com.chason._01_base.lesson1.demo5.split1.Demo5_1;

/**
 * 测试访问修饰符跨包的可见性情况 func1()
 */
public class Demo5_2 extends Demo5_1 {

    public static void main(String[] args) {
        func1();
    }

    private static void func1() {

        // 不同的包其他的都不能访问
        Demo5_1 fa = new Demo5_1();
        System.out.println(fa.number3);     // public
        // System.out.println(fa.number2); error 不能直接访问

        // protected修饰不在一个包只能子类自己的实例访问，不能直接访问父类的
        Demo5_2 son = new Demo5_2();
        System.out.println(son.number2);    // protected
        System.out.println(son.number3);    // public
    }
}
