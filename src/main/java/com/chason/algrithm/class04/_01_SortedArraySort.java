package com.chason.algrithm.class04;

/**
 * 给你一个数组arr，这个数组几乎有序(在数组排好序之后，每个位置的元素移动的距离都不超过K)
 * 给你 arr 和 k，排序数组，让时间复杂度尽可能的低
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题解: 当一个数组几乎有序，每个元素都可以移动很短的距离就可以了，例如 k = 2;意味着如果数组排好序
 * 那么 0 位置的数一定在 0,1,2 这三个位置上，因为如果3位置的数移动到0位置，就已经超过了2
 *
 * 基于以上：我们可以先将 前面(k+1)个位置的数放入一个小根堆 priorityQueue
 * 每次弹出一个，弹出的数必定是最小的数，所以从前到后补上，弹出一个，补上一个
 * 直到遍历完整个数组
 */
public class _01_SortedArraySort {

    public static void main(String[] args) {

        int[] arr = {3, 1, 4, 2, 6, 5};
        sort(arr, 2);

        System.out.println(arr);

    }

    public static void sort(int[] arr, int k) {

        if (arr == null || arr.length < 2) {
            return;
        }

        if (arr.length <= k) {  // 全部数组排序 那就随便找个 O(N * logN)的排序方式进行排序
            Arrays.sort(arr);
            return;
        }

        // 剩下的就是可以根据堆弹出规则排序

        PriorityQueue<Integer> queue = new PriorityQueue<>(new MyComparator()); // 小根堆
        for (int i=0; i<=k; i++) {
            queue.add(arr[i]);
        }

        int[] help = new int[arr.length];
        int index = 0;
        int addPoint = k+1;

        while (!queue.isEmpty()) {
            if (addPoint < arr.length) {
                help[index++] = queue.poll();
                queue.add(arr[addPoint++]);
            } else {
                help[index++] = queue.poll();
            }
        }

        // 将help数组刷回去
        for (int i=0; i<arr.length; i++) {
            arr[i] = help[i];
        }

    }

    // 默认小根堆，所以比较器不用写
    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}
