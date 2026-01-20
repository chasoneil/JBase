package com.chason.collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {


    public static void main(String[] args) {
        test();
    }

    private static void test() {

        List<String> mylist = new ArrayList<>();
        mylist.add("a");
        mylist.add("b");
        mylist.add("c");

        for (String s: mylist) {
            if (s.equals("b")) {
                mylist.remove(s);
            }
        }

        System.out.println(mylist);
    }


}
