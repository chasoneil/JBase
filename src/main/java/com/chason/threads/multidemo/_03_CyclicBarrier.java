package com.chason.threads.multidemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点（common barrier point）。
 * 练习： 设计一个程序，每次并行打印三个数，每个线程打印的时间不同（通过随机时间控制）
 * 当三个线程都打印完成，才可以进行下一次的打印。
 * 一共打印5次，从1-15
 */
public class _03_CyclicBarrier {

    private static int count = 1;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                // 一轮所有线程执行完，执行该方法
                System.out.println("本轮输出完成！");
            }
        });

        System.out.println("准备输出：");
        // 启动三个线程
        for (int i=0; i<3; i++) {
            new Thread(new NumberPrinter(5, cyclicBarrier)).start();
        }

    }

    // 输出1 4 7
    static class NumberPrinter implements Runnable {

        private final CyclicBarrier cyclicBarrier;

        private int rounds;

        public NumberPrinter (int rounds, CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            this.rounds = rounds;
        }

        @Override
        public void run() {
            // 一共输出3轮
            for (int i = 0; i < rounds; i++) {
                try {
                    long t = ((long) (Math.random() * 5)) * 1000;
                    Thread.sleep(t);
                    System.out.println(Thread.currentThread().getId() + ":" + count++);
                    // 等待同一批的其他线程执行完
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }




}
