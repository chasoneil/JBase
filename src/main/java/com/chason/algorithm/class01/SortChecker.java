package com.chason.algorithm.class01;

import com.chason.algorithm.class03._03_FastSort;
import com.chason.algorithm.utils.ArrayUtils;

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
            //Sort.selectSort(arr);

            // MergeSort.sort1(arr);
            // MergeSort.sort2(arr);

            _03_FastSort.sort(arr);
            Arrays.sort(arr1);

            if (!ArrayUtils.isEqual(arr, arr1)) {
                isOK = false;
                break;
            }
        }

        System.out.println(isOK ? "Pass" : "Failed");
    }


}
