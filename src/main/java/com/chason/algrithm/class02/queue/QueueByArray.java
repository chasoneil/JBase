package com.chason.algrithm.class02.queue;

public class QueueByArray {

    private int[] arr;

    private static final int LIMIT = 5;

    private int capacity;

    private int count;

    private int pushIdx;

    private int popIdx;

    public QueueByArray(int capacity) {
        capacity = capacity > LIMIT ? capacity : LIMIT;
        arr = new int[capacity];
        this.capacity = capacity;
    }

    public void offer(int value) {

        if (count == capacity) {
            throw new RuntimeException("Queue is full");
        }

        arr[pushIdx] = value;
        pushIdx = nextIdx(pushIdx);
        count++;
    }

    public int poll() {

        if (count == 0) {
            throw new RuntimeException("Queue is empty");
        }

        int val = arr[popIdx];
        popIdx = nextIdx(popIdx);
        count--;
        return val;
    }

    public int peek() {
        if (count == 0) {
            throw new RuntimeException("Queue is empty");
        }

        return arr[popIdx];
    }

    public int size() {
        return count;
    }


    public int nextIdx(int index) {
        return index == capacity-1 ? 0 : index+1;
    }

    public static void main(String[] args) {


        QueueByArray queue = new QueueByArray(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        // queue.offer(6);

        System.out.println(queue.peek());
        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }

}
