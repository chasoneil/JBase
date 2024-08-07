package com.chason.algrithm.class02.stack;

public class StackByArray {

    private int[] arr;

    private static final int LIMIT = 5;

    private int count;

    private int capacity;

    private int index;

    public StackByArray(int capacity) {
        capacity = capacity > LIMIT ? capacity : LIMIT;
        arr = new int[capacity];
        this.capacity = capacity;
    }

    public int pop() {

        if (count == 0) {
            throw new RuntimeException("Stack is empty");
        }

        int val = arr[--index];
        count--;
        return val;
    }

    public int peek() {
        if (count == 0) {
            throw new RuntimeException("Stack is empty");
        }

        return arr[index-1];
    }

    public void push(int value) {

        if (count == capacity) {
            throw new RuntimeException("Stack is full");
        }

        arr[index++] = value;
        count++;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {

        StackByArray stack = new StackByArray(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        // stack.push(6);

        System.out.println(stack.peek());
        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
