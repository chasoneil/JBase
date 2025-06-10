package com.chason._01_base.exercise.lesson1;

/**
 * 1. switch 面试题 func1()
 */
public class Test2 {

    public static void main(String[] args) {
        // func1();
        // func2();
        func3();
    }

    private static void func1() {
        int x = 6, y = 10, k = 5;
        switch (x % y) {
            case 0:
                k = x * y;
            case 6:
                k = x / y;
            case 12:
                k = x - y;
            default:
                k = x * y - x;
        }
        System.out.println("k=" + k);   // 54
    }

    private static void func2() {
        int i = 10, j = 18, k = 30;
        switch (j - i) {
            case 8:
                k++;
            case 9:
                k += 2;
            case 10:
                k += 3;
            default:
                k /= j;
        }
        System.out.println("k=" + k);
    }

    private static void func3() {

        switch (0) {
            default:                        // default 一定是最后，写在前面他也是最后
                System.out.println(5);
            case 0:
                System.out.println(0);
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
        }

    }

}
