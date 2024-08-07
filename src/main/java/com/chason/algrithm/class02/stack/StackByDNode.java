package com.chason.algrithm.class02.stack;

import com.chason.algrithm.class02.base.DNode;

public class StackByDNode {

    DNode head;

    DNode curr;

    int count;

    public int pop() {

        if (curr == null) {
            throw new RuntimeException("Stack is empty");
        }

        int val = (Integer) curr.value;
        curr = curr.prev;
        return val;
    }

    public void push(int value) {

        if (head == null) {
            head = curr = new DNode(value);
            head.prev = null;
            head.next = null;
        } else {
            curr.next = new DNode(value);
            DNode prev = curr;
            curr = curr.next;
            curr.prev = prev;
        }
        count++;
    }

    public int peek() {
        if (curr == null) {
            throw new RuntimeException("Stack is empty");
        }

        return (Integer) curr.value;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }


    public static void main(String[] args) {

        StackByDNode stack = new StackByDNode();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.peek());
        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }


}
