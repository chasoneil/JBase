package com.chason.algrithm.class03;

import com.chason.algrithm.utils.ArrayUtils;

public class FastSort {


    public static void sort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        //fastSort1(arr, 0, arr.length-1);
        fastSort2(arr, 0 ,arr.length-1);
    }

    /**
     * 快速排序 1.0  时间复杂度 O(N^2)
     * 每次都用 arr[R]做划分值
     * @param arr
     * @param L
     * @param R
     */
    public static void fastSort1(int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }

        int[] area = partition3(arr, L, R);
        fastSort1(arr, L, area[0] - 1);
        fastSort1(arr, area[1] + 1, R);
    }

    /**
     * 快排 2.0 版本
     * 不再使用固定的 arr[R] 作为划分值了
     * 使用随机一个位置的数作为划分值
     * @param arr
     */
    public static void fastSort2(int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }

        int randomPosition = (int) (Math.random() * (R - L + 1))  + L;
        ArrayUtils.swap(arr, R, randomPosition);
        int[] area = partition3(arr, L, R);
        fastSort2(arr, L, area[0] - 1);
        fastSort2(arr, area[1] + 1, R);
    }

    /**
     * 将一个随机数组 arr 进行调整，要求 <= target 的放在左边 > target放在右边
     * 要求： 时间复杂度 O(N) 空间复杂度 O(1)
     * @param arr
     * @param target
     */
    public static void partition1(int[] arr, int target) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int left = -1;  // <= 边界

        for (int i=0; i<arr.length; i++) {
            if (arr[i] <= target) {
                ArrayUtils.swap(arr, i, ++left);
            }
        }
    }

    /**
     * 将数组 arr 按照target 分成三份
     * < target 在最左边
     * = target 在中间
     * > target 在右边
     */
    public static void partition2(int[] arr, int target) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int left = -1;
        int right = arr.length;

        int index = 0; // current index

        // 当index 碰到右边界 停
        while (index < right) {

            if (arr[index] < target) {
                ArrayUtils.swap(arr, index++, ++left);
            } else if (arr[index] == target) {
                index++;
            } else {  // arr[index] > target
                ArrayUtils.swap(arr, index, --right);
            }
        }
    }

    /**
     * 对 arr[L...R] 执行 partition2 的操作
     * target = arr[R]
     * @param arr 无序数组
     * @return int[] 返回调整完成之后的 = target区域的左边界和右边界
     */
    public static int[] partition3(int[] arr, int L, int R) {

        if (L > R) {
            return new int[] {-1, -1};  // 返回一个无效边界
        }

        if (L == R) {
            return new int[] {L, R};
        }

        int target = arr[R];
        int left = L - 1;  // 左边界
        int right = R;      // 右边界

        int index = L;

        while (index < right) {

            if (arr[index] < target) {
                ArrayUtils.swap(arr, index++, ++left);
            } else if (arr[index] == target) {
                index++;
            } else {  // arr[index] > target
                ArrayUtils.swap(arr, index, --right);
            }

        }

        // left last arr element
        // 如果只有一个 target 那么执行完上面的结果 < target )( > target, target
        ArrayUtils.swap(arr, R, right);

        // 当处理完成之后数组的情况是这样的： < target) target,...,(target, > target
        // > 区域的第一个数已经被换过了，所以直接返回大于区域的下标即可
        return new int[] {left+1, right};
    }


    public static void main(String[] args) {

        int[] arr = {8, 6, 2, 4, 5, 1, 0, 7};

        int[] arr1 = {4, 2, 6, 2, 3, 1, 7, 4, 5, 9 ,0, 4};

        //partition1(arr, 4);

        //partition2(arr1, 4);

        int[] result = partition3(arr1, 0, arr.length-1);

        ArrayUtils.print(arr1);
        ArrayUtils.print(result);

    }

}
