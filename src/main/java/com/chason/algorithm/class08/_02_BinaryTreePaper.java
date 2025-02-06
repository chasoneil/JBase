package com.chason.algorithm.class08;

/**
 * 二叉树的折纸问题
 * 一张纸对折N次，从上到下打印折痕的方向
 */
public class _02_BinaryTreePaper {

    public static void main(String[] args) {
        int N = 3;
        printLine(N);
    }

    /*
     这个其实是一个有规律的
     1. 头节点是凹
     2. 所有左子树的都节点都是凹
     3. 所有右子树的头节点都是凸
     参数是对折的次数 N
     */
    public static void printLine(int N) {
        process(1, N, true);
    }

    /*
     i: 当前来到的层数
     N: 最大层数，是调用者给的，且不变
     down: 表示当前这个节点是不是down 如果是down 则为Ture
     想象一个节点：这个节点位于i层，目的是中序遍历这个树
     */
    public static void process(int i, int N, boolean down) {

        if (i > N) {
            return;
        }

        // 中序遍历的方式打印整棵树
        process(i+1, N, true);
        System.out.print(down ? "凹" : "凸");
        process(i+1, N, false);
    }


}
