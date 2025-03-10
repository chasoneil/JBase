package com.chason.threads.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _04_ThreadPool01 {

    public static void main(String[] args) {

        /*
         创建线程池的方式
         1. submit 和 execute的区别：
         execute 只能提交runnable submit能提交callable
         submit本质上是对execute的扩展, 执行的时候还是调了execute()
         submit如果传入的是一个Runnable ，他会自动帮你包一层Void类型的FutureTask
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        // = threadPool.execute(new MyRunnable());
        fixedThreadPool.submit(new MyRunnable());
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("New thread run, id:" + Thread.currentThread().getId());
        }
    }


}
