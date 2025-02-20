package com.chason.algorithm.class02;

public class _04_QueueByNode {

    public static class QueueByNode {

        private int size;
        Node head;
        Node tail;

        public QueueByNode () {
            size = 0;
            head = tail = null;
        }

        public int size() {
            return size;
        }

        public void add(int value) {
            if (head == null) {
                tail = head = new Node(value);
            } else {
                Node node = new Node(value);
                tail.next = node;   // tail = head ,在定义tail next的时候其实head next也同样被定义了
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        // 队列是先进先出 从头弹出
        public int poll() {

            if (size == 0) {
                throw new RuntimeException("Queue is empty");
            }

            int result = head.value;
            head = head.next;
            size--;
            return result;
        }

        public int peek() {

            if (size == 0) {
                throw new RuntimeException("Queue is empty");
            }

            return head.value;
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

        QueueByNode queue = new QueueByNode();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
