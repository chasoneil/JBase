package com.chason._01_base.lesson2.demo3;

/**
 * 这是子类
 * 1. 子类初始化，会先初始化父类 func1()
 * 2. 子类可以获取父类的public default 属性 func2()
 */
public class Demo3_2 extends Demo3_1 {

    public static void main(String[] args) {
        // func1();

        Demo3_2 demo = new Demo3_2();
        demo.func2();
    }

    /**
     * 只做子类的实例化但是父类构造方法一起被调用
     */
    private static void func1() {
        Demo3_2 son = new Demo3_2();
    }

    private void func2() {

        Demo3_2 son = new Demo3_2();
        System.out.println(son.str1);   // public
        System.out.println(son.str2);   // default

        son.str1 = "demo3_2 str1";
        System.out.println(son.str1);   // 修改子类中的str1不影响父类中的str1
        System.out.println(super.str1);
        super.str1 = "abc";             // 子类可以修改父类中的str1
        System.out.println(super.str1);

        son.getMsg1();      // public
        son.getMsg2();      // default
    }

    public Demo3_2() {
        System.out.println("Demo3_2 constructor");
    }

}
