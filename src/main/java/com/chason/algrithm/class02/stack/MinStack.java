package com.chason.algrithm.class02.stack;

import java.util.Stack;

/**
 * you can get stack min number by O(1)
 */
public class MinStack {

    private Stack data = new Stack();

    private Stack min = new Stack();

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

        MinStack stack = new MinStack();

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
