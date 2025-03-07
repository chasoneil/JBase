package com.chason.algorithm.class12;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 打印一个字符串的全部子序列
 * 子序列： 这个字符串的字符顺序不能变，但是每个字符可以选择也可以不选择，打印全部的结果
 * 例如： abc:
 * 他的子序列： "", "a", "ab", "ac", "b", "bc", "c", "abc"
 */
public class _02_PrintSubString {

    // 要求返回子序列的结果
    public static ArrayList<String> printSubString1(String string) {
        if (string == null) {
            return null;
        }

        char[] chs = string.toCharArray();
        String path = "";
        ArrayList<String> result = new ArrayList<>();
        process1(chs, 0, result,  path);
        return result;
    }

    public static ArrayList<String> printSubString2(String string) {
        if (string == null) {
            return null;
        }

        char[] chs = string.toCharArray();
        String path = "";
        ArrayList<String> result = new ArrayList<>();
        process2(chs, 0, result,  path);
        return result;
    }

    /**
     * 输出一个字符串的所有子序列，但是不能有重复的
     * @param string 字符串
     * @return 返回所有子序列的结果
     */
    public static ArrayList<String> printSubStringNoRepeat(String string) {

        if (string == null) {
            return null;
        }

        char[] chs = string.toCharArray();
        String path = "";
        HashSet<String> result = new HashSet<>();
        process3(chs, 0, result,  path);

        ArrayList<String> ans = new ArrayList<>();
        for (String tmp : result) {
            ans.add(tmp);
        }

        return ans;
    }

    // 标准写法
    public static void process1(char[] chs, int index, ArrayList<String> result, String path) {
        if (index == chs.length) {
            result.add(path);
            return;
        }

        // 选择了当前字符
        process1(chs, index+1, result, path + chs[index]);
        // 没选择当前字符
        process1(chs, index+1, result, path);
    }

    // 逻辑上更好理解
    public static void process2 (char[] chs, int index, ArrayList<String> result, String path) {

        if (index == chs.length) {
            return;
        }

        // 来到了index位置，选择index位置的字符
        String tmp = path + chs[index];
        result.add(tmp);
        process2(chs, index+1, result, tmp);

        // 没选择所以这个字符串上一步就已经添加过了pass
        process2(chs, index+1, result, path);
    }

    public static void process3 (char[] chs, int index, HashSet<String> result, String path) {

        if (index == chs.length) {
            return;
        }

        String tmp = path + chs[index];
        result.add(tmp);
        process3(chs, index+1, result, tmp);

        process3(chs, index+1, result, path);
    }


    private static void print(ArrayList<String> ans) {

        for (String str : ans) {
            System.out.println(str);
        }

        System.out.println(" ============================ ");

    }


    public static void main(String[] args) {

        // 测试标准写法和逻辑上易理解的写法区别
//        ArrayList<String> list1 = printSubString1("abbc");
//        print(list1);

        ArrayList<String> list2 = printSubString2("abbc");
        print(list2);

        ArrayList<String> list3 = printSubStringNoRepeat("abbc");
        print(list3);
    }

}
