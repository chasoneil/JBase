package com.chason.algorithm.class12;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全排列
 * 全排列： 所有的字符都需要，但是顺序不定
 * 例如字符串 "abc"
 * 全排列： abc, acb, bac, bca, cab, cba
 */
public class _03_PrintAllSubString {


    // 方法1：
    public static List<String> allSubstring1(String string) {

        List<String> ans = new ArrayList<>();
        if (string == null || string.isEmpty()) {
            return ans;
        }

        char[] chs = string.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (int i=0; i<chs.length; i++) {
            rest.add(chs[i]);
        }

        String path = "";
        process1(rest, path, ans);
        return ans;
    }

    // 方法2 优化的版本
    public static List<String> allSubstring2 (String string) {

        List<String> ans = new ArrayList<>();

        if (string == null || string.isEmpty()) {
            return ans;
        }

        char[] chs = string.toCharArray();
        process2(chs, 0, ans);
        return ans;
    }

    /**
     * 字符串的全排序，去重
     * @param string 字符串
     * @return
     */
    public static List<String> allSubstringNoRepeat (String string) {

        List<String> ans = new ArrayList<>();
        if (string == null || string.isEmpty()) {
            return ans;
        }

        char[] chs = string.toCharArray();
        process3(chs, 0, ans);
        return ans;
    }

    /*
        我们是通过交换数组中的字符位置来确定的，如果我们发现某个位置曾经出现过某个字符
        那么我们就不走这个分支，就实现了去重
        比如一个字符串 aba, 当出现  0 位置和2位置交换，发现a曾经出现在 0位置，这个分支就不走了
     */
    public static void process3 (char[] chs, int index, List<String> ans) {

        if (index == chs.length) {
            ans.add(String.valueOf(chs));
        } else {
            boolean[] visited = new boolean[256]; // 一共有的字符 ascii 码 255个
            for (int i=index; i<chs.length; i++) {
                if (!visited[chs[i]]) {  // 当前位置的字符没出现过
                    visited[chs[i]] = true;
                    swap(chs, index, i);
                    process3(chs, index+1, ans);
                    // 恢复现场
                    swap(chs, index, i);
                }
            }
        }
    }

    /**
     *
     * @param rest 数组中还剩下的字符串
     * @param path 当前已经选择好的字符串拼接的结果
     * @param ans  结果
     */
    public static void process1(ArrayList<Character> rest, String path, List<String> ans) {

        // 不剩字符了，意味着本次已经结束了，得到的是一个完整的结果
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            // 还剩下字符
            int N = rest.size();
            // 从剩下的字符中选择一个继续走下去
            for (int i=0; i<N; i++) {
                char ch = rest.get(i);
                // 把已经选择的从rest中踢掉
                rest.remove(i);
                // 这里path不能变
                process1(rest, path + ch, ans);

                /*
                (!!!)
                    重点：需要恢复现场
                    因为你选择了一个字符，例如abc 你选择了a ，将a从rest中移除，那么接下俩递归能选的是bc
                    但是当你这条路走完了，返回a的时候，你接下来要首先选择b, 但是你a已经移除了，所以要加回来保持最初的现场
                    选择b 将b从rest中移除，递归能选择的就是ac
                 */
                rest.add(i, ch);
            }
        }
    }

    /**
     *
     * @param chs  需要处理的字符数组，由字符串组成
     * @param index 当前来到的index
     * @param ans  结果
     *
     * 思路: 对于任意字符串 例如 abc
     * 0位置和0位置的字符交换 1位置和1位置的字符交换， 2位置和2位置的字符交换 => abc
     * 0位置和0位置的字符交换 1位置和2位置的字符交换， 2位置和2位置的字符交换 => acb
     * 0位置和1位置的字符交换 1位置和1位置的字符交换， 2位置和2位置的字符交换 => bac
     * 0位置和1位置的字符交换 1位置和2位置的字符交换， 2位置和2位置的字符交换 => bca
     * 0位置和2位置的字符交换 1位置和1位置的字符交换， 2位置和2位置的字符交换 => cba
     * 0位置和2位置的字符交换 1位置和2位置的字符交换， 2位置和2位置的字符交换 => cab
     *
     */
    public static void process2(char[] chs, int index, List<String> ans) {

        if (index == chs.length) { // 表示本轮交换已经结束了，该出结果了
            ans.add(String.valueOf(chs));
        } else {
            // 当前来到的位置是index 那么index之前的已经决定了
            // index 只能跟之后的进行交换
            for (int i=index; i<chs.length; i++) {
                swap(chs, index, i);
                // 继续往下面走
                process2(chs, index+1, ans);

                // (!!!) 恢复现场，记得给换回来
                swap(chs, index, i);
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }


    private static void print(List<String> ans) {
        for (String str : ans) {
            System.out.println(str);
        }

        System.out.println("=============================");
    }

    // ============== 测试 ==============
    public static void main(String[] args) {

        List<String> list1 = allSubstring1("abc");
        print(list1);

        List<String> list2 = allSubstring2("abc");
        print(list2);

        // 去重
        List<String> list3 = allSubstringNoRepeat("acc");
        print(list3);
    }

}
