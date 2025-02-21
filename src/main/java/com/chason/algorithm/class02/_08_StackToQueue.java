package com.chason.algorithm.class02;

import java.util.Stack;

/**
 * 使用栈结构实现队列
 */
public class _08_StackToQueue {

    public static class QueueByStack {

        Stack<Integer> push = new Stack<>();
        Stack<Integer> pop = new Stack<>();

        public void add(int ele) {
            pushToPop();
            push.push(ele);
        }

        /*
        每次poll之前，都执行pushToPop，如果pop中数据已经pop完了，也会从push中重新拿数据过来
         */
        public int poll() {
            pushToPop();
            return pop.pop();
        }

        public int peek() {
            pushToPop();
            return pop.peek();
        }

        /**
         * 倒数据：从push -> pop
         * 1. 只有pop为空才能倒
         * 2. 要倒就把全部数据倒过来
         */
        public void pushToPop() {
            if (pop.isEmpty()) {
                while (!push.isEmpty()) {
                    pop.push(push.pop());
                }
            }
        }
    }

    // ========== 测试 ==========
    public static void main(String[] args) {

        QueueByStack queue = new QueueByStack();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        // FIFO 期望输出 1 2 3 4
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
