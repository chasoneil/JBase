package com.chason._01_base.lesson1;

/**
 * 循环 switch case  func1()
 * switch 的表达式可以是: byte char short int  JDK7 以后可以是 String
 */
public class Demo6 {

    public static void main(String[] args) {
        func1();
    }

    private static void func1() {

        int today = 3;

        switch(today) {
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
                System.out.println("星期六");
                break;
            case 7:
                System.out.println("星期日");
                break;
            default:
                System.out.println("错误的星期");
                break;
        }
    }
}
