package com.chason.algorithm.class12;

/**
 * 汉诺塔问题：
 * 有三个塔（柱子或者其他类似）
 * 这三个塔分别是左中右
 * 在最左边的塔上有三个圆盘分别是小中大
 * 一开始的累积方式是 小压中压大
 *   |    |    |
 *   |    |    |
 *   |    |    |
 *   -    |    |
 *  ---   |    |
 * -----  |    |
 *   左   中   右
 *   不能违反 小压大的原则，每次只能移动一个圆盘，
 *   将这三个圆盘挪到最右的塔上
 *   打印每步的挪动数
 *   给定的参数是汉诺塔的层数 N
 */
public class _01_HanuoTower {

    public static void hanuo1(int N) {
        leftToRight(N);
    }

    /**
     * 移动汉诺塔一共分为三个步骤：
     * 1. 将除了最下面的圆盘之外的其他圆盘移动到中间
     * 2. 将最下面的圆盘移动到右边
     * 3. 将中间的圆盘移动到右边
     * @param N 汉诺塔的层数
     */
    public static void leftToRight(int N) {

        if (N == 1) {
            System.out.println("move 1 from left to right");
            return;
        }

        leftToMid(N - 1);
        System.out.println("move " + N + " from left to right");
        midToRight(N - 1);
    }

    public static void leftToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }

        leftToRight(N - 1);
        System.out.println("move " + N + " from left to mid");
        rightToMid(N - 1);
    }

    public static void midToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }

        midToLeft(N - 1);
        System.out.println("move " + N + " from mid to right");
        leftToRight(N - 1);
    }

    public static void rightToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to left");
            return;
        }

        rightToMid(N - 1);
        System.out.println("move " + N + " from right to left");
        leftToMid(N - 1);
    }

    public static void midToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }

        midToRight(N - 1);
        System.out.println("move " + N + " from mid to left");
        rightToLeft(N - 1);
    }

    public static void rightToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }

        rightToLeft(N - 1);
        System.out.println("move " + N + " from right to mid");
        leftToMid(N - 1);
    }

    /**
     * 对方法1的抽象，放弃左中右的想法
     * 使用 from to other来代替
     * 所以上面六个过程其实可以抽象为
     * 1. N-1 from -> other
     * 2. N from -> to
     * 3. N-1 other -> to
     * @param N
     */
    public static void hanuo2(int N) {
        process(N, "left", "right", "mid");
    }

    public static void process(int N, String from, String to, String other) {

        if (N == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }

        process(N-1, from, other, to);
        System.out.println("move " + N + " from " + from + " to " + to);
        process(N-1, other, to, from);
    }


    public static void main(String[] args) {
        hanuo1(3);
        System.out.println("==========");
        hanuo2(3);
    }

}
