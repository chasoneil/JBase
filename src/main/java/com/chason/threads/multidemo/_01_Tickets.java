package com.chason.threads.multidemo;

/**
 * 多线程模拟抢票
 * 重点：
 * 1. 面向对象思想
 * 2. 怎么保证票不会出现负数
 */
public class _01_Tickets {

    public static void main(String[] args) {

        TicketsSale sale = new TicketsSale();

        Thread u1 = new Thread(sale, "张三");
        Thread u2 = new Thread(sale, "李四");
        Thread u3 = new Thread(sale, "王五");
        Thread u4 = new Thread(sale, "赵六");

        u1.start();
        u2.start();
        u3.start();
        u4.start();
    }

    // 这里不能通过继承 Thread 类实现，因为tickets是共享资源
    // 通过继承Thread就要创建多个类，多个类，就有多个tickets
    // 所以本题的重点是，怎么保证tickets是共享资源，使用的时候还要被锁住
    static class TicketsSale implements Runnable {

        private static int tickets = 10;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (tickets == 0) {
                        System.out.println("票抢完啦！");
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + "抢到了：" + tickets--);
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

}
