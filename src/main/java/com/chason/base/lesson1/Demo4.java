package com.chason.base.lesson1;

/**
 * 1. Java 局部变量 func1()
 * 2. Java 实例变量 func2()
 * 3. Java 类变量（全局静态） func3()
 * 4. Java 参数变量 func4()
 */
public class Demo4 {

    /* 实例变量，属于类的实例，在实例初始化的时候会给默认值 */
    private int number;

    private static int count;

    public static void main(String[] args) {

        // func1();

        // Demo4 demo4 = new Demo4();
        // demo4.func2();

        // func3();

        func4();
    }

    /**
     * 局部变量: 方法内部，方法结束就销毁，局部变量一定要初始化才能用
     */
    private static void func1() {

        int a, b, c;  // 没有初始化是不能用的
        int x=1, y=2;
        float f = 3.2f;     // float 的数组后面一定要有后缀f/F
        byte z = 22;
        double pi = 3.14159;  // double 后面没有后缀

        String s = "Hello Java";
        char ch = 'a';
        // System.out.println("a:" + a); error 编译不过
        System.out.println("x:" + x);
    }

    /* 实例变量 */
    private void func2() {

        // 这个变量不初始化也能用,实例初始化的时候被给默认值，多个实例就初始化多次
        System.out.println("number default:" + this.number);
    }

    /* 全局静态变量只初始化一次 */
    private static void func3() {
        System.out.println("count default:" + count);
    }

    private static void func4() {
        int num = 5;
        System.out.println("before:" + num);  // 5
        changeNumber(num);
        System.out.println("after:" + num); // 5
    }

    /* 传递的参数只有在函数内部被修改，函数结束就失效 */
    private static void changeNumber (int num) {
        num = 10;
        System.out.println("changeNum:" + num); // 10
    }


}


