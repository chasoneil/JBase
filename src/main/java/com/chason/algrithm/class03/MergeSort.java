package com.chason.algrithm.class03;

public class MergeSort {


    public static void sort1(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        doSort(arr, 0, arr.length-1);

    }


    /**
     * let arr sorted from L to R
     * @param arr
     * @param L
     * @param R
     */
    public static void doSort(int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }

        int m = L + (R-L)/2;

        doSort(arr, L, m);
        doSort(arr, m+1, R);

        doMerge(arr, L, m , R);
    }

    /**
     * let arr sorted
     * the arr was sorted from index L to m AND m+1 to R
     * @param arr
     * @param L
     * @param m
     * @param R
     */
    public static void doMerge(int[] arr, int L, int m, int R) {

        int[] help = new int[R-L+1];

        int idx = 0;
        int p1 = L;
        int p2 = m+1;

        while (p1 <= m && p2 <= R) {
            help[idx++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[idx++] = arr[p1++];
        }

        while (p2 <= R) {
            help[idx++] = arr[p2++];
        }

        for (int i=0; i<help.length; i++) {
            arr[L+i] = help[i];
        }
    }

    /**
     * 非递归的方式进行mergeSort
     */
    public static void sort2(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int step = 1; // 步长

        while (step < arr.length) {

            // target : 获取每次merge 的 L， M， R

            int L = 0;  // 左边界 左组

            while (L < arr.length) {

                int M = L + step - 1;   // 左组的右边界
                if (M >= arr.length) {
                    break;
                }

                int R = Math.min(M + step, arr.length - 1);  // 右组的右边界
                doMerge(arr, L, M, R);
                L = R + 1;
            }

            if (step > arr.length / 2) {  // 防溢出
                break;
            }
            step = step * 2;
        }
    }


}
