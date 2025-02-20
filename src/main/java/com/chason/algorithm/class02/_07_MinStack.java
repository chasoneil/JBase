package com.chason.algorithm.class02;

import java.util.Stack;

/**
 * you can get stack min number by O(1)
 */
public class _07_MinStack {

    private Stack<Integer> data = new Stack<>();

    private Stack<Integer> min = new Stack<>();

    public int pop() {
        min.pop();
        return (Integer) data.pop();
    }

    public void push(int value) {

        data.push(value);
        if (min.isEmpty()) {
            min.push(value);
        } else {
            int pushValue = (Integer) min.peek() > value ? value : (Integer) min.peek();
            min.push(pushValue);
        }
    }

    public int peek() {
        return (Integer) data.peek();
    }

    public int getMin() {
        return (Integer) min.peek();
    }


    public static void main(String[] args) {

        _07_MinStack stack = new _07_MinStack();

        stack.push(3);
        System.out.println(stack.getMin());

        stack.push(2);
        System.out.println(stack.getMin());

        stack.push(4);
        System.out.println(stack.getMin());

        stack.push(1);
        System.out.println(stack.getMin());

        stack.pop();
        System.out.println(stack.getMin());

        stack.pop();
        System.out.println(stack.getMin());

        stack.pop();
        System.out.println(stack.getMin());

        stack.pop();
        System.out.println(stack.getMin());
    }

}
