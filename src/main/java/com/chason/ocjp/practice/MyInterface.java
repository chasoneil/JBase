package com.chason.ocjp.practice;

/**
 * 1. 接口中的方法都是抽象方法
 */
public interface MyInterface {

    // 其实是一个抽象方法 只是省略了 abstract
    void m();

    // 添加 abstract 也是正确的
    abstract void m1();

    // public 关键字也是省略的
    public abstract void m2();

    // public 和 abstract 关键字都可以省略或者部分省略
    public void m3();

    // JDK 1.8 之后就有了 default 方法
    default void m4() {
        System.out.println("This is default method: m4()");
    }

    // public 关键字可以省略
    public default void m5() {
        System.out.println("This is default method: m5()");
    }

    // default 方法可以有返回值，当然也可以有方法体
    default int m6(){
        return 1;
    }

    // JDK 9之后，接口可以有私有方法
    private void m7() {
        System.out.println("This is private method");
    }

    // 私有方法当然也可以有返回值
    private int m8(){
        return 8;
    }
}
