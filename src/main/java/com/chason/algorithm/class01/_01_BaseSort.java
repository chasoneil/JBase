package com.chason.algorithm.class01;

import com.chason.algorithm.class03._03_FastSort;
import com.chason.algorithm.utils.ArrayUtils;

import java.util.Arrays;

/**
 * simpleSort :
 * 1. select sort
 * 2. insert sort
 * 3. bubble sort
 *
 */
public class _01_BaseSort {


    public static void selectSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i=0; i<arr.length; i++) {
            int minIndex = i;
            for (int j=i+1; j<arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            ArrayUtils.swap(arr, minIndex, i);
        }
    }


    public static void bubbleSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i=arr.length-1; i>0; i--) {
            for (int j=0; j<i; j++) {
                if (arr[j] > arr[j+1]) {
                    ArrayUtils.swap(arr, j, j+1);
                }
            }
        }

    }

    /**
     * 0-0 sorted √
     * 0-1 sorted ?
     * ...
     * 0-N sorted ?
     * @param arr
     */
    public static void insertSort (int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        // 当 指针到 i 表示 i 个位置是当前需要处理数，那么虚拟的，已经处理的区域就是 i-1
        // 也就是说 0 - i-1 已经有序了
        // 第i 个数和第 i-1个数做对比，可以理解为向前看，如果前面比我小，那我继续向前看，直到看到最前的位置，
        // 或者前面已经不比我小了，那么就不看了
        for (int i=0; i<arr.length; i++) {
            for (int j=i-1; j>=0 && arr[j] > arr[j+1]; j--) {
                ArrayUtils.swap(arr, j, j+1);
            }
        }
    }

    // ======== 对数器 ===========
    public static void main(String[] args) {

        int maxValue = 100;
        int maxSize  = 100;
        int testTime = 100000;

        boolean suc = true;

        for (int i=0; i<testTime; i++) {

            int[] arr = ArrayUtils.buildRandomArray(maxValue, maxSize);
            int[] arr1 = ArrayUtils.copyArray(arr);
            insertSort(arr);
            Arrays.sort(arr1);
            if (!ArrayUtils.isEqual(arr, arr1)) {
                suc = false;
                break;
            }
        }

        System.out.println(suc ? "Pass" : "Failed");
    }

}
