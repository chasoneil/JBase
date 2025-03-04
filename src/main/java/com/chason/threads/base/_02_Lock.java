package com.chason.threads.base;

/**
 * yield 让出CPU进入就绪状态
 */
public class _02_Lock {

    private static int count = 1;

    private static final Object lock = new Object();

    public static void main(String[] args) {

        System.out.println("main thread start");



    }

    private static void lock() {



    }


    private static void noLock() {
        System.out.println("start test");
        MyThreadD t1 = new MyThreadD();
        MyThreadS t2 = new MyThreadS();

        t1.start();
        t2.start();
    }

    static class MyThread1 extends Thread {

    }


    static class MyThreadD extends Thread {

        @Override
        public void run() {

            while (true) {
                if (count % 2 == 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("================= D print " + count);
                    count++;
                } else {
                    Thread.currentThread().yield();
                }
            }
        }
    }

    static class MyThreadS extends Thread {

        @Override
        public void run() {

            while (true) {
                if (count % 2 == 1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("================= S print " + count);
                    count++;
                } else {
                    Thread.currentThread().yield();
                }
            }
        }
    }


}


