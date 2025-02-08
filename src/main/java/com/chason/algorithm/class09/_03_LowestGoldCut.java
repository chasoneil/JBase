package com.chason.algorithm.class09;

import java.util.PriorityQueue;

/**
 * 一块金条要做分割，每次分割需要的铜板数是金条的长度
 * 例如一个长度为20 的金条，分割他一定需要20个铜板
 * 给定一个数组 数组和是需要分割的金条的长度， 数组中的个数就是需要分割的个数
 * 问： 怎么分割才能耗费最低
 *
 * 例： {10, 20, 30} 给定一个数组，说明金条长度是60, 如果先切10 50 那么花费60
 * 再切 20 30 花费50 总花费110
 * 如果先切30 30 花费60  再切 10 20 花费30 总花费90
 *
 * 哈夫曼编码问题
 */
public class _03_LowestGoldCut {

    /*
     准备一个小根堆放整数，然后将数组全部入堆
     每次都弹出堆顶的两个数，相加放回堆里
     直到最后堆里只剩一个数
     每次弹出的两个数和合并的数可以组成一个二叉树，这个二叉树的相加数的节点就是最优方案
     */
    public static int lowestCut1(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        PriorityQueue<Integer> heap1 = new PriorityQueue<>();

        for (int i=0; i<arr.length; i++) {
            heap1.add(arr[i]);
        }

        int result = 0;
        // 每次弹出两个数
        while (heap1.size() > 1) {
            int num1 = heap1.poll();
            int num2 = heap1.poll();
            result += (num1 + num2);
            heap1.add(num1 + num2);
        }

        return result;
    }


}
