package com.chason.test.base.exception;

/**
 * return的本质是什么
 */
public class _01_TryAndReturn {

    public static void main(String[] args) {
        Too too = new Too();
        StringBuilder t1 = test(too);
        System.out.println("return语句返回的:" + t1 + "\t返回值的hashCode:" + t1.hashCode());
        // 这里值是finally hashCode和test方法中的finally输出相同
        System.out.println("finally里面修改的:" + too.num + "\tfinally的hashCode:" + too.num.hashCode());
    }

    public static StringBuilder test(Too too) {
        try {
            too.num = new StringBuilder("try");
            System.out.println("1：" + too.num);     // print try

            // 这两个不一样是因为不是一个对象
            System.out.println("try字符串的hashcode:" + ("try").hashCode());
            System.out.println("Too里的try的hashCode:" + too.num.hashCode());

            // 将地址返回，所以返回值的hashCode和Too里的一样
            return too.num;
        } finally {
            too.num = new StringBuilder("finally");//语句3
            System.out.println("finally的hashCode:" + too.num.hashCode());//语句4
        }
    }
}

// 关注类初始化的过程
class Too {
    StringBuilder num = new StringBuilder("你好");
}


