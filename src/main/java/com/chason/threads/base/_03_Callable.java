package com.chason.threads.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable创建线程并使用
 * 和Runnable有什么不同
 */
public class _03_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main thread ID :" + Thread.currentThread().getId());

        Thread t1 = new Thread(new MyRunnable());

        try {
            t1.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        FutureTask<String> task = new FutureTask<>(new MyCallable());
        Thread t2 = new Thread(task);
        t2.start();

        // 如果不get，那么异常也不会被捕获
        String result = task.get();
        System.out.println(result);

    }

    static class MyCallable implements Callable<String> {

        /*
        1. 实现的方法不同，callable 是 call
        2. call() 会抛出异常
        3. call() 有返回值 返回值类型自己指定
         */
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getId() + " Callable running.");

            return "Callable message";
            // throw new RuntimeException("Callable test exception");
        }
    }

    static class MyRunnable implements Runnable {

        /*
        1. 实现的方法是run()
        2. run() 不会排除异常
        3. run() 没有返回值
         */
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + " Runnable running.");
            throw new RuntimeException("Runnable test exception");
        }
    }

}
