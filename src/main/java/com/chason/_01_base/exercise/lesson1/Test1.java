package com.chason._01_base.exercise.lesson1;

/**
 * 数组的测试01
 * 输出的结果是?
 * 1 2 3 4 5
 */
public class Test1 {

    public static void main ( String[] args )
    {
        int[] myArray = {1, 2, 3, 4, 5};

        /*
            Java所有的都是值传递，如果是给了一个引用，也只是引用的副本
            在出了方法之后，引用远地址指向的区域不变
            你可以通过这个引用的副本更改里面的内容，但是你没法修改引用本身
        */
        ChangeIt.doIt(myArray);
        for (int j=0; j<myArray.length; j++) {
            System.out.print( myArray[j] + " " );
        }
    }
}

class ChangeIt {
    static void doIt( int[] z ) {
        z = null ;
    }
}
