package com.chason.algorithm.class02;

/**
 * 使用数组实现队列
 */
public class _03_QueueByArray {

    public static class QueueByArray {

        private int[] arr;

        private int size;

        private int capacity;

        private int offerIndex;

        private int pollIndex;

        public QueueByArray (int capacity) {
            if (capacity <= 0) {
                throw new RuntimeException("Capacity is under 0.");
            }

            this.capacity = capacity;
            arr = new int[capacity];
            size = 0;
            offerIndex = pollIndex = 0;
        }

        public void offer(int ele) {
            if (size == capacity) {
                throw new RuntimeException("Queue is full!");
            }
            arr[offerIndex] = ele;
            offerIndex = nextIndex(offerIndex);
            size++;
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("Queue is empty.");
            }
            int result = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            size--;
            return result;
        }

        public int peek() {
            if (size == 0) {
                throw new RuntimeException("Queue is empty.");
            }
            return arr[pollIndex];
        }

        private int nextIndex(int index) {
            if (index == capacity-1) { // 只有到边界才变
                return 0;
            } else {
                return index+1;
            }
        }

        public int size() {
            return size;
        }

    }

    // === 测试 ====
    public static void main(String[] args) {

        QueueByArray queue = new QueueByArray(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        // queue.offer(6);

        System.out.println(queue.peek());
        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
