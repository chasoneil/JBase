package com.chason.algrithm.utils;

import java.util.Map;

public class ArrayUtils {

    public static void swap (int[] arr, int i, int j) {

        if (arr == null || arr.length < 2 || i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void print(int[] arr) {

        if (arr == null) {
            throw new RuntimeException("Array is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i=0; i<arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length-1) {
                sb.append(",");
            }
        }

        sb.append("]");
        System.out.println(sb.toString());

    }


    public static int[] buildRandomArray (int maxValue, int maxSize) {

        if (maxSize < 0) {
            throw new RuntimeException("Max size can't be negative");
        }

        int randomSize = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[randomSize];

        for (int i=0; i<randomSize; i++) {
            int randomValue = ((int) (Math.random() * maxValue) + 1) - ( (int) (Math.random() * maxValue) + 1);
            arr[i] = randomValue;
        }
        return arr;
    }

    public static int[] copyArray (int[] arr) {

        if (arr == null) {
            throw new RuntimeException("Array is null");
        }

        int[] copy = new int[arr.length];

        for (int i=0; i<arr.length; i++) {
            copy[i] = arr[i];
        }

        return copy;
    }

    public static boolean isEqual(int[] src, int[] dst) {

        if (src == null || dst == null) {
            return false;
        }

        if (src.length != dst.length) {
            return false;
        }

        for (int i=0; i<src.length; i++) {
            if (src[i] != dst[i]) {
                return false;
            }
        }

        return true;
    }


}
