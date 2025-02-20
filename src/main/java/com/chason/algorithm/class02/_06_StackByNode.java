package com.chason.algorithm.class02;

public class _06_StackByNode {

    public static class StackByNode {

        int size;
        Node head;
        Node tail;

        public StackByNode () {
            size = 0;
            head = tail = null;
        }

        public void push(int value) {
            if (head == null) {
                head = tail = new Node(value);
            } else {
                Node node = new Node(value);
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        // stack 先入后出
        public int pop() {
            if (size == 0) {
                throw new RuntimeException("Stack is empty");
            }

            // 从尾部弹出
            int result = tail.value;
            tail = tail.prev;
            size--;
            return result;
        }

        public int peek() {
            if (size == 0) {
                throw new RuntimeException("Stack is empty");
            }
            return tail.value;
        }

        public int size () {
            return size;
        }
    }

    public static class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // ==========  测试 ===========
    public static void main(String[] args) {

        StackByNode stack = new StackByNode();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
