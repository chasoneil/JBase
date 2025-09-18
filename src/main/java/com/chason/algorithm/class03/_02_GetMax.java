package com.chason.algorithm.class03;

/**
 * 使用递归的方式实现获取一个数组中的最大值
 */
public class _02_GetMax {

    public static int getMax(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        return process(arr, 0, arr.length - 1);
    }

    /**
     * 获取arr L...R 中的最大值
     * 将一个数组拆分成两个部分，然后再根据两个或者多个部分得到的结果合并最终的结果
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int process(int[] arr, int L, int R) {

        if (L >= R) {  // 一定要注意这里的边界，如果不是 >= 则会栈溢出
            return arr[L];
        }

        int M = (R - L)/2 + L;
        int leftMax = process(arr, L, M);
        int rightMax = process(arr, M + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static int getMax2(int[] arr) {

        if (arr == null || arr.length < 1) {
            return -1;
        }

        int max = arr[0];

        for (int i=1; i<arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }

        return max;
    }

    // ======= 对数器 =========
    public static void main(String[] args) {

        int maxValue = 100;
        int maxLength = 10;
        int testTime  = 100000;

        boolean suc = true;
        for (int i=0; i<testTime; i++) {
            int[] arr = generateArray(maxLength, maxValue);
            if (getMax(arr) != getMax2(arr)) {
                suc = false;
                break;
            }
        }

        System.out.println(suc ? "Pass!" : "Failed!");
    }

    private static int[] generateArray(int maxLength, int maxValue) {
        int rLength = (int) (Math.random() * maxLength);
        int[] arr = new int[rLength];

        for (int i=0; i<rLength; i++) {
            arr[i] = (int)(Math.random() * maxValue) - (int)(Math.random() * maxValue);
        }
        return arr;
    }


}
