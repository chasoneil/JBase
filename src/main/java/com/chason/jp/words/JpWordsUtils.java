package com.chason.jp.words;

import java.util.Scanner;

public class JpWordsUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        start();

    }

    // 开始练习
    private static void start() {
        System.out.println("欢迎进行日文单词练习,请选择课程(1-1),按回车结束.");
        String res = scanner.next();
        checkInput(res);

        int s1 = Integer.parseInt(res);
        init(s1);
    }

    // 初始化单词数据
    private static void init(int classNumber) {



    }

    private static void checkInput(String input) {

        try {
            int s = Integer.parseInt(input);
            if (s <= 0) {
                throw new RuntimeException("输入错误,请输入大于0的整数.");
            }
        } catch (NumberFormatException e) {
            System.out.println("必须输入正整数");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.exit(-1);


    }


}
