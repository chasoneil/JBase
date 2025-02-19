package com.chason.algorithm.class02;

// 数组实现栈
public class _05_StackByArray {

    private static class StackByArray {

        private int[] arr;
        private int capacity;
        private int pushIndex;
        private int popIndex;
        private int size;

        public StackByArray(int capacity) {
            if (capacity < 0) {
                throw new RuntimeException("Capacity is under 0.");
            }

            this.capacity = capacity;
            arr = new int[capacity];
            pushIndex = popIndex = 0;
            size = 0;
        }

        public void push(int ele) {
            if (size == capacity) {
                throw new RuntimeException("Stack is full!");
            }
            arr[pushIndex++] = ele;
            popIndex++;
            size++;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("Stack is empty!");
            }

            int result = arr[--popIndex];
            pushIndex--;
            size--;
            return result;
        }

        public int peek() {
            if (size == 0) {
                throw new RuntimeException("Stack is empty!");
            }

            return arr[popIndex-1];
        }

        public int size() {
            return size;
        }

    }



    public static void main(String[] args) {

        StackByArray stack = new StackByArray(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
    }


}
