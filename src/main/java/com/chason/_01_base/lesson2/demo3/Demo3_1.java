package com.chason._01_base.lesson2.demo3;

/**
 * 这是父类
 */
public class Demo3_1 {

    public String str1 = "demo3_1 str1";

    String str2 = "demo3_1 str2";

    private String str3 = "demo3_1 str3";

    public String getMsg1 () {
        return "This is public method -> getMsg1()";
    }

    String getMsg2 () {
        return "This is default method -> getMsg2()";
    }

    private String getMsg3() {
        return "This is private method -> getMsg3()";
    }

    public Demo3_1() {
        System.out.println("Demo3_1 constructor");
    }

}
