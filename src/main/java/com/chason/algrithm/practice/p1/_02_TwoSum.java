package com.chason.algrithm.practice.p1;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和  number: 1
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 */
public class _02_TwoSum {

    public static void main(String[] args) {

        int[] nums = {3, 2, 4};
        _02_TwoSum twoSum = new _02_TwoSum();
        int[] result = twoSum.twoSum2(nums, 6);
        System.out.println(result);
    }

    /*
        常规思路 : 取一个数，遍历一次找另一个数
        时间复杂度 O(n^2)
     */
    public int[] twoSum(int[] nums, int target) {

        if (nums == null || nums.length < 1) {
            return new int[] {-1, -1};
        }

        for (int i=0; i<nums.length; i++) {
            int num2 = target - nums[i];

            for (int j=0; j<nums.length; j++) {
                if (nums[j] == num2 && j != i) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }

    /*
        使用hash表
        将 数组遍历一遍 key 数组中的值 value 对应值的index
        重新遍历一遍，这次找不再是遍历数组了，而是直接从hash表中获取元素，时间复杂度O(N)
        总体的时间复杂度 O(N)
        因为是两数之和 , 所以就算出现相同的答案 比如 target = 6 ,结果是 3， 3
        那么hash表中存放的是一定是后一个3的位置，而你遍历的时候一定是第一次就拿到第一个3的位置
        所以就算出现相同的元素，不影响结果可以直接覆盖
     */
    public int[] twoSum2 (int[] nums, int target) {

        if (nums == null || nums.length < 1) {
            return new int[] {-1, -1};
        }

        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            hashMap.put(nums[i], i);
        }

        for (int j=0; j<nums.length; j++) {
            int num2 = target - nums[j];
            if (hashMap.get(num2) != null && hashMap.get(num2) != j) {
                return new int[] {j, hashMap.get(num2)};
            }
        }

        return new int[] {-1, -1};
    }

}
