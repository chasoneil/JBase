package com.chason.threads.multidemo;

import java.util.concurrent.Semaphore;

/**
 * 信号量Semaphore 是用来控制对共享资源的访问的工具。
 * 信号量的概念来源于信号与锁的概念。信号量是一个计数器，用来控制对共享资源的访问。
 * 多个线程可以同时访问共享资源，但是只有指定数量的线程能同时访问。
 *
 * 模拟信号量的使用场景：
 * 1. 限制并发访问数量为2
 * 2. 五个线程同时争资源，只有两个线程能同时访问共享资源
 */
public class _04_Semaphore {

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(2);

        for (int i=0; i<5; i++) {
            new Thread(new MyWorker(semaphore)).start();
        }

    }

    static class MyWorker implements Runnable {

        private final Semaphore semaphore;

        public MyWorker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getId() + " is waiting...");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getId() + " is running...");
                Thread.sleep((long) (Math.random() * 1000));
                semaphore.release();
                System.out.println(Thread.currentThread().getId() + " is done.");
            } catch (InterruptedException e) {

            }
        }
    }


}
