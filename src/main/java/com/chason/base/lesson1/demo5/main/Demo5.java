package com.chason.base.lesson1.demo5.main;

/**
 * 访问修饰符：
 * 1. public default protected private
 * 全可见 public 仅本类可见 private
 * 本包可见 default protected
 * default同包都可见，不同包都不可见
 * protected同包都可见，不同包（子类可见，本实例不可见）
 * 代码案例见 Demo5_1 Demo5_2 Demo5_3
 *
 * 2. transient
 * 该访问修饰符仅用于是否持久化，序列化的时候用的最多，被这个关键字修饰则不会进行序列化，自然而然，值就带不过去了
 * 可以提高序列化的效率，同时避免一些安全问题，比如密码就不用序列化传输
 *
 * 3. volatile
 * 这个关键字是强制内存同步的，使用这个关键字变量在使用之前会去共享内存同步一次
 * 在更改之后也会强制同步刷到共享内存中
 */
public class Demo5 {

    public static void main(String[] args) {

    }
}
