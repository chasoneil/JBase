package com.chason.threads.multidemo;

import java.util.concurrent.CountDownLatch;

/**
 * 1. CountDownLatch 是用来控制线程同步的工具类，它允许一个或多个线程等待其他线程完成各自的工作后再继续执行。
 * 2. 使用CountDownLatch实现一个百米赛跑的功能
 * 要求： 有五个运动员，和校长，校长发令五个运动员开始跑，五个运动员都到了终点，校长才能宣布比赛结束。
 */
public class _02_CountDownLatch {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(5);

        System.out.println("校长: 预备，开始！");
        for (int i=0; i<5; i++) {
            new Thread(new Runner(latch, i + "号")).start();
        }

        try {
            // 只有当latch = 0 的时候，latch 之后的线程才会继续执行
            latch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("校长：比赛结束！");
    }

    static class Runner implements Runnable {

        private final CountDownLatch latch;

        private final String name;

        public Runner(CountDownLatch latch, String name) {
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.name + " 出发!");
            try {
                // 模拟跑步的时间 5s 内的随机时间
                Thread.sleep(((int) (Math.random() * 5)) * 1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(this.name + " 抵达终点!");

            // 当执行完一个线程， countdown 一次
            latch.countDown();
        }
    }
}
