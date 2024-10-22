package com.chason.algrithm.practice.p1;

/**
 * https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked
 * Number: 283
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class _01_MoveZero {

    public static void main(String[] args) {

        int[] arr = new int[] {0, 1, 0, 2, 3};

        _01_MoveZero moveZero = new _01_MoveZero();
        moveZero.moveZeroes(arr);

        System.out.println(arr);

    }

    /*
        双指针，p1, p2 初始位置都是0
        p2 正常遍历 p1指向上次出现0的位置
        当 arr[p2] == 0  p2++
        当 arr[p2] != 0  交换p1 p2的位置  p1++ p2++
     */
    public void moveZeroes(int[] nums) {

        if (nums == null || nums.length < 1) {
            return;
        }

        int p1 = 0;  // 记录上一个0出现的位置
        int p2 = 0;
        for (; p2 < nums.length; p2++) {

            if (nums[p2] == 0) {
                // do nothing
            } else {
                swap(nums, p1, p2);
                p1++;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
