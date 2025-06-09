package com.chason._01_base.lesson1;

/**
 * Java 注释 func1()
 */
public class Demo2 {
    public static void main(String[] args) {
        func1();
    }

    private static void func1() {
        int a; // 单行注释

        /* 单行或者多行 */
        int b;

        /*
            也可以多行
            第二行
         */
        int c;

        /**
         * 多行注释，也是文档级注释，可以用来生成文档
         * @Param
         * @Return 等会帮助文档的生成
         */
        int d;
    }

}
