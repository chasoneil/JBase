package com.chason.algrithm.sort.simple;

import com.chason.algrithm.utils.ArrayUtils;

import java.util.ArrayList;

/**
 * simpleSort :
 * 1. select sort
 * 2. insert sort
 * 3. bubble sort
 *
 */
public class Sort {


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

        for (int i=0; i<arr.length; i++) {
            for (int j=i-1; j>=0 && arr[j] > arr[j+1]; j--) {
                ArrayUtils.swap(arr, j, j+1);
            }
        }
    }


    public static void main(String[] args) {

//        int[] arr1 = {3, 1, 8, 2, 9, 4, 1, 7};
//        int[] arr2 = {};
//        int[] arr3 = {1};
//        int[] arr4 = {2,2,2};
//
//        bubbleSort(arr1);
//        bubbleSort(arr2);
//        bubbleSort(arr3);
//        bubbleSort(arr4);
//
//        insertSort(arr1);
//        insertSort(arr2);
//        insertSort(arr3);
//        insertSort(arr4);
//
//        ArrayUtils.print(arr1);
//        ArrayUtils.print(arr2);
//        ArrayUtils.print(arr3);
//        ArrayUtils.print(arr4);

        SortChecker.start();

    }




}
