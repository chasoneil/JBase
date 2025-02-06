package com.chason.algorithm.class09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定一个由字符串组成的数组 strs
 * 按照任意顺序 将数组串成一个字符串
 * 要求 字符串字典序最小
 *
 */
public class _01_MinSortString {

    /*
        贪心策略：
        对于任意两个字符串 m n
        如果 m.n < n.m 则m放在前，n放在后
     */
    public static String getMinSort1(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }

        // 对数组按照对应的排序规则进行排序
        Arrays.sort(strs, new MyComparator());
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String getMinSort2(String[] strs) {
        if (listAll(strs).isEmpty()) {
            return "";
        } else {
            return listAll(strs).first();
        }
    }

    // 列出所有可能排列的字符串，结果放到treeSet中
    // treeSet会自己排序(有序表) 这样只要取第一个字符串就行
    public static TreeSet<String> listAll (String[] strs) {

        TreeSet<String> result = new TreeSet<>();
        if (strs == null || strs.length < 1) {
            result.add("");
            return result;
        }

        for (int i=0; i<strs.length; i++) {
            // 依次选择每个字符串当头
            String first = strs[i];
            String[] next = removeIndex(strs, i);
            TreeSet<String> list = listAll(next);
            for (String str : list) {
                result.add(first + str);
            }
        }

        return result;
    }

    public static String[] removeIndex (String[] strs, int index) {
        int length = strs.length;
        String[] help = new String[length - 1];
        int helpIndex = 0;
        for (int i=0; i<strs.length; i++) {
            if (i != index) {
                help[helpIndex++] = strs[i];
            }
        }
        return help;
    }

    // -------------- 对数器 ---------------
    public static void main(String[] args) {

        String[] strs = new String[] {"b", "ba"};

        String result = getMinSort2(strs);
        System.out.println(result);

    }


}
