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


    public static int lowestCut2 (int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0);
    }

    /*
        arr 是剩余的数组数量等待合并
        done 已经切割的代价
     */
    public static int process(int[] arr, int done) {

        int result = Integer.MAX_VALUE;
        // 如果最后数组中合并的只剩一个了，结束
        if (arr.length == 1) {
            return done;
        }

        for (int i=0; i<arr.length; i++) {
            for (int j=i+1; j<arr.length; j++) {
                result = Math.min(process(mergeArr(arr, i, j), done + arr[i] + arr[j]), result);
            }
        }

        return result;
    }

    /*
    将arr i 和 j 位置的数合并成一个数，然后返回合并后的数组
     */
    private static int[] mergeArr(int[] arr, int i, int j) {

        int[] help = new int[arr.length - 1];

        int sum = 0;
        int index = 0;
        for (int k=0; k<arr.length; k++) {
            if (k != i && k != j) {
                help[index++] = arr[k];
            } else {
                sum += arr[k];
            }
        }

        help[index] = sum;
        return help;
    }

    // =========  对数器 =============

    public static void main(String[] args) {

        int maxSize = 6;
        int maxValue = 100;
        int testTime = 100000;

        boolean suc = true;
        for (int i=0; i<testTime; i++) {
            int[] arr1 = createRandomArr(maxSize, maxValue);
            int[] arr2 = copyArr(arr1);
            if (lowestCut1(arr1) != lowestCut2(arr2)) {
                suc = false;
                break;
            }
        }

        System.out.println(suc ? "Pass!" : "Failed!");
    }


    private static int[] createRandomArr(int maxSize, int maxValue) {
        int rSize = (int) (Math.random() * maxSize) + 1;
        int[] result = new int[rSize];

        for (int i=0; i<rSize; i++) {
            result[i] = (int) (Math.random() * maxValue) + 1;
        }
        return result;
    }

    private static int[] copyArr(int[] arr) {

        if (arr.length < 1) {
            return new int[0];
        }

        int[] help = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            help[i] = arr[i];
        }

        return help;
    }
}
