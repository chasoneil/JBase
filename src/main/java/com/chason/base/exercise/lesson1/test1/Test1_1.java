package com.chason.base.exercise.lesson1.test1;

/**
 * 对Test1的证明
 */
public class Test1_1 {

    public static void main(String[] args) {
        // func1();
        func2();
    }

    private static void func1() {
        int[] arr = {1, 2, 3, 4, 5};
        doIt(arr);
        printArray(arr);  // 1 2 3 4 5  值传递不改变原来的

        System.out.println();
        swap(arr, 1, 3);
        printArray(arr);    // 可以通过引用的副本修改对象内容
    }

    private static void func2() {
        int[] arr = {1, 2, 3, 4, 5};
        doChange(arr);
        printArray(arr);    // 99 2 3 4 5 就算是副本也能改
    }

    private static void swap(int[] arr, int x, int y) {
        if (x == y) {
            return;
        }

        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void doIt(int[] arr) {
        arr = null;
    }

    private static void printArray(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void doChange(int[] arr) {
        int[] at = arr;
        at[0] = 99;
    }

}
