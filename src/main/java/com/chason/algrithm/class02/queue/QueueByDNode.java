package com.chason.algrithm.class02.queue;

import com.chason.algrithm.class02.base.DNode;

public class QueueByDNode {

    DNode head;

    DNode tail;

    int count;

    public int poll() {

        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }

        int val = (Integer) head.value;
        head = head.next;
        return val;
    }

    public void offer(int value) {

        if (head == null) {
            head = tail = new DNode(value);
            head.prev = null;
            head.next = null;
        } else {
            DNode node = new DNode(value);
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        count++;
    }

    public int peek() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }

        return (Integer) head.value;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0 ? true : false;
    }

    public static void main(String[] args) {

        QueueByDNode queue = new QueueByDNode();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println(queue.peek());
        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
