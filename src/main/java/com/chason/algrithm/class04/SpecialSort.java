package com.chason.algrithm.class04;

import java.util.HashMap;
import java.util.Map;

/**
 * 计数排序  桶排序的一种，本质上就是利用容器来进行排序，使用空间换时间
 * 计数排序不基于比较
 */
public class SpecialSort {

    public static void main(String[] args) {

        int[] ages = {20, 32, 18, 55, 43, 25, 32};

        sort01(ages);
        System.out.println(ages);
    }

    /**
     * 给你一个整形数组，里面存放的是员工的年龄，将员工的年龄按照从小到大进行排序
     * 要求：时间复杂度尽量的低  (PS: 员工年龄 18 - 60)
     *
     * 时间复杂度 O(N)
     */
    public static void sort01(int[] ages) {

        if (ages == null || ages.length < 2) {
            return;
        }

        // 因为员工年龄在一个固定区间
        // key : age value: 这个age的人数
        Map<Integer, Integer> ageMap = new HashMap<>();
        for (int i=18; i<61; i++) {
            ageMap.put(i, 0);
        }

        for (int i=0; i<ages.length; i++) {
            ageMap.put(ages[i], ageMap.get(ages[i])+1);
        }

        int index = 0;
        for (int i=18; i<61; i++) {
            if (ageMap.get(i) != 0) {
                int times = ageMap.get(i);
                while (times > 0) {
                    ages[index++] = i;
                    times--;
                }
            }
        }
    }

}
