package com.chason.ocjp.practice;

import java.util.Iterator;
import java.util.List;

public class Practice01 {

    public static int sum(List list) {

        int sum =0;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {

            int i = ((Integer) iterator.next()).intValue();
            sum += i;
        }
        return sum;
    }



}
