package com.chason.algorithm.class04;

/**
 * 使用数组实现一个堆结构
 * 主要是为了实现 heapIfy/heapInsert
 */
public class _01_Heap {

    // 大根堆
    public static class MaxHeap {

        int[] arr;
        int capacity;
        int size;

        public MaxHeap (int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
        }

        /*
        在以 arr 组织的堆结构中，任意节点的数发生变化，那么如果这个数是需要往下看的
        执行该方法
         */
        public void heapIfy(int[] arr, int index, int heapSize) {

            int left = 2 * index + 1;
            while (left < heapSize) { // 左节点不越界，右节点单论

                int maxIndex = arr[index] >= arr[left] ? index : left;

                int right = left + 1;
                if (right < heapSize) {  // 有右节点
                    maxIndex = arr[maxIndex] >= arr[right] ? maxIndex : right;
                }

                // 如果发现自己是最大的，那就不用往下看了
                if (maxIndex == index) {
                    break;
                }
                swap(arr, index, maxIndex);
                index = maxIndex;
                left = 2 * index + 1;
            }
        }

        /*
         在使用arr组织的堆结构中，向index位置新增一个数
         保证堆结构
         大根堆
         */
        public void heapInsert(int[] arr, int index) {
            while (index > 0 && arr[index] > arr[(index -1)/2]) {
                swap(arr, index, (index-1)/2);
                index = (index-1)/2;
            }
        }

        public boolean isEmpty () {
            return size == 0;
        }

        public int size() {
            return size-1;
        }

        public boolean isFull() {
            return size == capacity;
        }

        // 向堆中添加元素
        public void add(int value) {
            if (isFull()) {
                throw new RuntimeException("Heap is full!");
            }
            arr[size] = value;
            heapInsert(arr, size++);
        }

        // 从堆中弹出元素
        public int poll() {
            if (isEmpty()) {
                throw new RuntimeException("Heap is empty!");
            }
            int result = arr[0];
            swap(arr, 0, --size);
            heapIfy(arr, 0, size);
            return result;
        }

        public static void swap(int[] arr, int i, int j) {
             if (i == j) {
                 return;
             }

             int tmp = arr[i];
             arr[i] = arr[j];
             arr[j] = tmp;
        }
    }

    // ============= test ==========
    public static void main(String[] args) {

        int[] arr = {3, 1, 5, 2, 7, 6};

        MaxHeap heap = new MaxHeap(10);

        for (int i=0; i<arr.length; i++) {
            heap.add(arr[i]);
        }

        for (int i=0; i<10; i++) {
            System.out.println(heap.poll());
        }
    }

}
