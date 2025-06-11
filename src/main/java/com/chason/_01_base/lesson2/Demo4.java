package com.chason._01_base.lesson2;

/**
 * 方法的重载
 * 重写是发生在父子类之间的，重载则是本类中对方法的重新编写
 */
public class Demo4 {


    public static void main(String[] args) {
        func1();
    }

    private static void func1() {

        Demo4 demo4 = new Demo4();
        demo4.method1("方法1");
        demo4.method1("方法2", 1);

    }

    public void method1(String msg) {
        System.out.println("method1 ->" + msg);
    }

    // 参数不同也是方法的重载
    public void method1(String msg, int num) {
        System.out.println("method1 -> " + msg + ", num -> " + num);
    }

}
