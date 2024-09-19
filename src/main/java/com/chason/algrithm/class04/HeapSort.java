package com.chason.algrithm.class04;

/**
 * 堆： 大根堆或者小根堆
 * 大根堆： 在一个完全二叉树中，每个子树的最大值都是头节点的值
 * 小根堆： 在一个完全二叉树中，每个子树的最小值都是都节点的值
 * 堆肯定是一个完全二叉树
 * 完全二叉树： 一个二叉树从左往右满的或者在变满的过程中是一个完全二叉树
 *
 * 任何一个完全二叉树都可以使用数组进行表示
 * 假如 数组 arr 的任意下标 i 对应的arr[i] 的元素是一个二叉树中的节点
 * 那么他的左子节点位置是 2*i+1
 * 右子节点的位置是 2*i+2
 * 他的父节点的位置是 i-1/2
 */
public class HeapSort {

    // 堆排序
    // 时间复杂度 O(N * logN)
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int heapSize = arr.length;

        // 从上往下建大根堆 时间复杂度 O(N * logN)
//        for (int i=0; i<arr.length; i++) {
//            heapInsert(arr, i);         // 一个个往堆中添加元素
//        }
        // 从下往上建大根堆 时间复杂度 O(N)
        for (int i=heapSize-1; i>=0; i--) {
            heapIfy(arr, i, heapSize);
        }

        // 上述步骤结束，整个数组是一个大根堆结构
        swap(arr, 0, --heapSize); // 将最大的数调整到数组末尾

        // 继续交换，直到heapSize 变成0 结束O(N)
        // 交换和调整的时间复杂度 O(N * logN)
        while (heapSize > 0) {
            heapIfy(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 向数组arr中新增一个数，刚开始的位置是 index
     * 使数组保持大根堆的结构
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        // 不断往上看的过程
        // 这个判断条件其实包含两个情况 1. index=0 根节点，肯定不满足 2. index != 0 子节点和父节点做比较
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    /**
     * 从头结点往下沉
     * @param arr
     * @param index
     * @param heapSize
     */
    private static void heapIfy(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;

        while (left < heapSize) {  // 如果左孩子都越界，那么右孩子一定越界

            // 看左右俩孩子谁比较大，得出较大的标
            int largeIndex = left+1 < heapSize && arr[left] < arr[left+1] ? left+1 : left;
            // 让左右孩子中较大的值和当前节点比较大小，得到较大的下标
            largeIndex = arr[index] > arr[largeIndex] ? index : largeIndex;
            if (largeIndex == index) { // 说明当前是最大了，左右孩子都没自己大，结束
                break;
            }
            swap(arr, index, largeIndex);
            index = largeIndex;
            left = 2 * index + 1; // 为了让循环继续走下去
        }
    }


    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
