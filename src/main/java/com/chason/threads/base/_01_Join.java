package com.chason.threads.base;

/**
 * test join() method
 * join() 表示将当前的线程和调用他的线程合并，直到当前线程执行完，调用线程才能执行
 */
public class _01_Join {

    public static void main(String[] args) {

        System.out.println("main thread start.");

        JoinThread jt = new JoinThread();
        jt.start();
        try {
            jt.join();
            System.out.println("main thread running.");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("main thread end.");
    }
}

class JoinThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i=0; i<10; i++) {
                System.out.println("Join thread running " + i);
                Thread.sleep(500);
            }
            System.out.println("Join thread end.");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
