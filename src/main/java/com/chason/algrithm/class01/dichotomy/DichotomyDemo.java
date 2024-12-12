package com.chason.algrithm.class01.dichotomy;

/**
 * dichotomy can solve some type of problems
 *
 * 1. check target exist in a sorted arr
 * 2. check nearest left position >= target
 * 3. check nearest right position <= target
 */
public class DichotomyDemo {

    public static boolean isExist (int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return false;
        }

        if (target < arr[0]) {
            return false;
        }

        if (target > arr[arr.length-1]) {
            return false;
        }

        int L = 0;
        int R = arr.length - 1;

        int M;

        while (L<=R) {

            M = L + (R-L)/2;
            if (arr[M] == target) {
                return true;
            } else if (arr[M] > target) {
                R = M-1;
            } else {
                L = M+1;
            }
        }

        return false;
    }

    /**
     * >= target -> nearest left
     * @param arr
     * @param target
     * @return
     */
    public static int nearestLeft (int[] arr, int target) {

        if (arr == null || arr.length < 1) {
            return -1;
        }

        if (target < arr[0]) {
            return 0;
        }

        if (target > arr[arr.length-1]) {
            return -1;
        }

        int L = 0;
        int R = arr.length -1;

        int m;

        // {1,1,1,1,1,2,2,2,2,3,3,3}  target = 2
        while (L<=R) {

            m = L + (R-L)/2;
            if (arr[m] < target) {
                L = m+1;
            } else {
                R = m-1;
            }
        }

        return L;
    }

    public static boolean isExistBase (int[] arr, int target) {

        if (arr == null || arr.length < 1) {
            return false;
        }

        if (target < arr[0]) {
            return false;
        }

        if (target > arr[arr.length-1]) {
            return false;
        }

        for (int i=0; i<arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
        }

        return false;
    }

    public static int nearestLeftBase(int[] arr, int target) {

        if (arr == null || arr.length < 1) {
            return -1;
        }

        if (target < arr[0]) {
            return 0;
        }

        if (target > arr[arr.length-1]) {
            return -1;
        }

        for (int i=0; i<arr.length; i++) {
            if (arr[i] >= target) {
                return i;
            }
        }

        return -1;
    }


    public static void main(String[] args) {

        // DichotomyChecker.isExistStarter();

        DichotomyChecker.nearestLeftStarter();
    }

}
