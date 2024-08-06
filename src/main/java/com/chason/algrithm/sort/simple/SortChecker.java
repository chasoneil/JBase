package com.chason.algrithm.sort.simple;

import com.chason.algrithm.utils.ArrayUtils;

import java.util.Arrays;

public class SortChecker {


    public static void start () {

        int testTime = 100000;
        int maxValue = 100;
        int maxSize  = 100;

        boolean isOK = true;

        for (int i=0; i<testTime; i++) {

            int[] arr = ArrayUtils.buildRandomArray(maxValue, maxSize);
            int[] arr1 = ArrayUtils.copyArray(arr);

            //Sort.insertSort(arr);

            //Sort.bubbleSort(arr);
            Sort.selectSort(arr);
            Arrays.sort(arr1);

            if (!ArrayUtils.isEqual(arr, arr1)) {
                isOK = false;
                break;
            }
        }

        System.out.println(isOK ? "Pass" : "Failed");
    }


}
