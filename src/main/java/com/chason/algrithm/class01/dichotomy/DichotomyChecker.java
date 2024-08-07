package com.chason.algrithm.class01.dichotomy;

import com.chason.algrithm.utils.ArrayUtils;

public class DichotomyChecker {

    public static void isExistStarter () {

        int testTime = 100000;
        int maxValue = 100;
        int maxSize  = 100;

        boolean isOK = true;

        for (int i=0; i<testTime; i++) {

            int randomTarget = ((int)(Math.random() * maxValue) + 1) - ((int)(Math.random() * maxValue) + 1);
            int[] arr = ArrayUtils.buildSortedArray(maxValue, maxSize);

            if (DichotomyDemo.isExist(arr, randomTarget) != DichotomyDemo.isExistBase(arr, randomTarget)) {
                isOK = false;
                break;
            }
        }

        System.out.println(isOK? "Pass" : "Failed");
    }

    public static void nearestLeftStarter() {

        int testTime = 100000;
        int maxValue = 100;
        int maxSize  = 100;

        boolean isOK = true;

        for (int i=0; i<testTime; i++) {

            int randomTarget = ((int)(Math.random() * maxValue) + 1) - ((int)(Math.random() * maxValue) + 1);
            int[] arr = ArrayUtils.buildSortedArray(maxValue, maxSize);

            if (DichotomyDemo.nearestLeft(arr, randomTarget) != DichotomyDemo.nearestLeftBase(arr, randomTarget)) {
                isOK = false;

                ArrayUtils.print(arr);
                System.out.println("target:" + randomTarget);
                break;
            }
        }

        System.out.println(isOK? "Pass" : "Failed");
    }

}
