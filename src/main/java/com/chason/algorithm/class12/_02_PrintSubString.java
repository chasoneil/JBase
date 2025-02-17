package com.chason.algorithm.class12;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列上
 * 子序列： 这个字符串的字符顺序不能变，但是每个字符可以选择也可以不选择，打印全部的结果
 * 例如： abc:
 * 他的子序列： "", "a", "ab", "ac", "b", "bc", "c", "abc"
 */
public class _02_PrintSubString {

    public static void printSubString(String string) {
        if (string == null) {
            return;
        }

        char[] chs = string.toCharArray();
        String path = "";
        process1(chs, 0, path);
    }

    // 有的题要求返回子序列的结果
    public static List<String> printSubString2(String string) {
        if (string == null) {
            return null;
        }

        char[] chs = string.toCharArray();
        String path = "";
        List<String> result = new ArrayList<>();
        process2(chs, 0, result,  path);
        return result;
    }

    /**
     * @param chs 传入的String转成的字符数组
     * @param index 当前来到的index
     * @param path 已经决定了的字符行程的字符串path
     */
    public static void process1(char[] chs, int index, String path) {
        if (index == chs.length) {
            return;
        }

        // 选择了当前字符
        String result = path + chs[index];
        System.out.println(result);
        process1(chs, index+1, result);

        // 没选择不打印，因为之前肯定打印过了
        result = path;
        process1(chs, index+1, result);
    }

    public static void process2 (char[] chs, int index, List<String> result, String path) {

        if (index == chs.length) {
            return;
        }

        // 来到了index位置，选择index位置的字符
        String tmp = path + chs[index];
        result.add(tmp);
        process2(chs, index+1, result, tmp);

        // 没有选择index位置
        tmp = path;
        // 没选择所以这个字符串上一步就已经添加过了pass
        process2(chs, index+1, result, tmp);
    }


    public static void main(String[] args) {
        // printSubString("abc");

        printSubString2("abc");
    }

}
