package com.chason.algorithm.class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列结构实现栈
 */
public class _09_QueueToStack {

    /*
    使用两个队列结构，push的时候正常进队列，出队列的时候，将数据队列数据导出到只有一个数
    然后执行pop, 再将两个队列的角色互换
     */
    public static class StackByQueue {

        Queue<Integer> push = new LinkedList<>();
        Queue<Integer> pop = new LinkedList<>();

        public void push(int ele) {
            push.offer(ele);
        }

        public int pop() {
            while (push.size() > 1) {
                pop.offer(push.poll());
            }

            int result = push.poll();
            pushToPop();
            return result;
        }

        public int peek() {

            if (push.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }

            while (push.size() > 1) {
                pop.add(push.poll());
            }

            int result = push.peek();
            pushToPop();
            return result;
        }

        // 将push 和 pop 的角色互换
        public void pushToPop() {
            Queue<Integer> help = push;
            push = pop;
            pop = help;
        }
    }

    // ========= 测试 ===========
    public static void main(String[] args) {

        StackByQueue stack = new StackByQueue();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
