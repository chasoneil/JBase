package com.chason.algorithm.class03;

/**
 * 数组小和问题：
 * 数组小和： 在一个数组中，对于任一位置index, index左边比他小的数的和
 * 例如： 数组 {3, 4, 2, 5}中
 * 对于3 小和是0 对于4 小和是3 对于2 小和是0 对于5 小和是3+4+2 = 9
 * 所以整个数组的小和是 0+3+0+9 = 12
 */
public class _04_SmallSum {

    /*
    分析数组小和的本质，是获取数组中小于当前位置的数
    对于数组小和的定义 = 对于当前任意index的数arr[index]  求所有arr[index]位置右边有多少个数比他大
    流程：执行mergesort
    在merge的过程中，拷贝左组的数产生小和，拷贝右组的数不产生小和
    如果两个数相等，则先拷贝右组的数，不产生小和
     */
    public static int smallSum1(int[] arr) {

        if (arr == null || arr.length < 1) {
            return 0;
        }

        return process(arr, 0, arr.length-1);
    }

    // arr L...R 上merge
    public static int process(int[] arr, int L, int R) {

        if (L >= R) {
            return 0;
        }

        int M = (R - L)/2 + L;

        int leftSum = process(arr, L , M);
        int rightSum = process(arr, M+1, R);

        int mergeSum = merge(arr, L, M, R);

        // 数组左边merge产生的小和 + 右边merge产生的小和 + 最终merge产生的小和
        return leftSum + rightSum + mergeSum;
    }

    // 数组 arr L...M 有序  M+1...R 有序
    // 执行merge 让整个数组有序，merge的过程中产生小和
    public static int merge(int[] arr, int L, int M, int R) {

        int[] help = new int[R-L+1];

        int p1 = L;
        int p2 = M+1;

        int idx = 0;
        int res = 0; // 小和

        while(p1 <= M && p2 <= R) {
            // 左组小产生小和
            // 产生 右组中有多少个数比arr[p1]大的个数个小和
            if (arr[p1] < arr[p2]) {
                res += (R-p2+1) * arr[p1];
                help[idx++] = arr[p1++];
            }  else {  // 剩余情况都是拷贝右组，不产生小和
                help[idx++] = arr[p2++];
            }
        }

        // 右组已经全部merge完了 此时没有右组，所以虽然产生小和但是个数为0
        while (p1 <= M) {
            help[idx++] = arr[p1++];
        }

        // 左组结束，右组不产生小和
        while (p2 <= R) {
            help[idx++] = arr[p2++];
        }

        for (int i=0; i<help.length; i++) {
            arr[L+i] = help[i];
        }

        return res;
    }

    /*
    使用暴力的方式获取数组小和
    时间复杂度 O(N^2)
     */
    public static int smallSum2(int[] arr) {

        if (arr == null || arr.length < 1) {
            return 0;
        }

        // outer index = i
        int res = 0;
        for (int i=0; i<arr.length; i++) {
            // inner j < index
            for (int j=0; j<i; j++) {
                if (arr[j] < arr[i]) {
                    res += arr[j];
                }
            }
        }

        return res;
    }


    // ============= 对数器 ===========
    public static void main(String[] args) {

        int maxValue = 100;
        int maxSize = 10;
        int testTime = 100000;

        boolean suc = true;
        for (int i=0; i<testTime; i++) {

            int[] arr = generateRandomArray(maxValue, maxSize);
            int[] copy = copyArray(arr);
            if (smallSum1(arr) != smallSum2(copy)) {
                suc = false;
                break;
            }
        }

        System.out.println(suc ? "Pass!" : "Failed!");

    }


    public static int[] generateRandomArray(int maxValue, int maxSize) {

        int rSize = (int) (Math.random() * maxSize);
        int[] arr = new int[rSize];
        for (int i=0; i<rSize; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {

        int[] copy = new int[arr.length];
        for (int i=0; i<copy.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
}
