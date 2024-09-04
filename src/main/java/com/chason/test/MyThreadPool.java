package com.chason.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThreadPool {

    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workerThreads;
    private final AtomicBoolean isShutdown;

    // 初始化的时候指定线程池的线程数
    public MyThreadPool(int numberOfThreads) {

        // 初始化阻塞队列
        taskQueue = new LinkedBlockingQueue<>();
        // 初始化线程池数组
        workerThreads = new Thread[numberOfThreads];
        isShutdown = new AtomicBoolean(false);

        // 当线程池启动的时候，线程直接运行
        for (int i = 0; i < numberOfThreads; i++) {
            workerThreads[i] = new WorkerThread();
            workerThreads[i].start();
        }
    }

    // 提交的任务只是放到了阻塞队列中
    public void execute(Runnable task) {
        if (!isShutdown.get()) {
            taskQueue.offer(task);
        }
    }

    // shutdown 不同于 shutdownNow()
    public void shutdown() {
        isShutdown.set(true);
        for (Thread workerThread : workerThreads) {
            workerThread.interrupt();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown.get() || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    if (isShutdown.get()) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        // 初始化一个线程池  3
        MyThreadPool threadPool = new MyThreadPool(3);

        // 尝试往里面丢任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            threadPool.execute(() -> {

                try {
                    Thread.sleep(2 * 1000);
                    System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        //System.out.println("线程池执行完毕...");
        //threadPool.shutdown();
    }
}
