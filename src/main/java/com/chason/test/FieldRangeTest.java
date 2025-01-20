package com.chason.test;

public class FieldRangeTest {

    static int index = 0;

    public static void main(String[] args) {
        // func1();
        // func2();
        func3();
    }

    public static void func1() {
        int index = 0;
        System.out.println("before :" + index);
        addIndex(index);
        System.out.println("after :" + index);
    }

    public static void func2() {
        System.out.println("before:" + index);
        addIndex(index);
        System.out.println("after:" + index);
    }

    public static void func3() {
        System.out.println("before:" + index);
        addStaticIndex();
        System.out.println("after:" + index);
    }

    public static void addIndex(int index) {
        index++;
        ++index;
    }

    public static void addStaticIndex() {
        index++;
    }


}
