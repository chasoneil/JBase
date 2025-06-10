package com.chason._01_base.lesson1;

import java.util.Scanner;

/**
 * 1. 循环 switch case  func1()
 * switch 的表达式可以是: byte char short int  JDK7 以后可以是 String
 *
 * 2. 练习：模拟一个系统的登录，如果三次输错密码，则锁定 func2()
 * 3. 递归练习 求一个数的阶乘 func3()
 * 4. 递归练习 求斐波那契数列 func4()
 */
public class Demo6 {

    public static void main(String[] args) {
        // func1();
        // func2();
        // func3();
        func4();
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

    /**
     * 模拟一个登录系统，要求输入用户名密码，如果输错三次则锁定用户
     */
    private static void func2() {

        System.out.println("请登录Shop系统！");
        Scanner sc = new Scanner(System.in);

        for (int i=0; i<3; i++) {
            System.out.println("请输入用户名：");
            String username = sc.nextLine();
            System.out.println("请输入密码：");
            String password = sc.nextLine();
            if ("admin".equals(username) && "admin".equals(password)) {
                System.out.println("登录成功！");
                return;
            } else {
                System.out.println("用户名密码校验失败，您还有" + (2-i) + "次机会。");
                continue;
            }
        }

        System.out.println("对不起，您的账号已被锁定！");
    }

    // 阶乘
    private static void func3() {
        int result = fac(4);
        System.out.println("4的阶乘为：" + result);
    }

    // 斐波那契数列
    private static void func4() {

        for (int i=1; i<=10; i++) {
            System.out.println("fab(" + i +")" + "=" + fab(i));
        }

    }

    private static int fac(int N) {
        if (N == 1) {
            return 1;
        }

        return N * fac(N-1);
    }

    private static int fab(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }

        return fab(N-2) + fab(N-1);
    }
}
