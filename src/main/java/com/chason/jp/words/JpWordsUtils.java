package com.chason.jp.words;

import com.chason.jp.pojo.JpWord;
import com.chason.jp.utils.CheckUtil;
import com.chason.jp.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class JpWordsUtils {

    private static Scanner scanner = new Scanner(System.in);

    private static List<JpWord> contains = new ArrayList<>();

    public static void main(String[] args) {
        start();
    }

    // 开始练习
    private static void start() {
        System.out.println("欢迎进行日文单词练习,请选择文件序号:");
        System.out.println("当前可选择的序号(1-2),请选择:");
        String res = scanner.next();
        CheckUtil.checkInput(res);
        int f1 = Integer.parseInt(res);

        System.out.println("请选择课程号,按回车结束。");
        String cStr = scanner.next();
        CheckUtil.checkInput(cStr);
        int c1 = Integer.parseInt(cStr);

        System.out.println("开始初始化单词数据...");
        init(f1, c1);

        System.out.println("初始化单词数据完成。");
        System.out.println("请选择练习类型: 1-根据单词假名解释含义 2-根据单词解释含义 3-根据含义填写假名 4-根据含义填写单词");
        String eType = scanner.next();
        CheckUtil.checkInput(eType);
        int e1 = Integer.parseInt(eType);

        doExercise(e1);
    }

    private static void doExercise(int eType) {
        switch (eType) {
            case 1:
                doWuShiToMean();
                break;
            case 2:
                doWordToMean();
                break;
            case 3:
                doMeanToWuShi();
                break;
            case 4:
                doMeanToWord();
                break;
            default:
                System.out.println("输入错误，练习结束。");
                System.exit(0);
        }
    }

    private static void doWuShiToMean() {

        int len = contains.size();
        int rGroup = (int) (Math.random() * len) + 3;
        int group = rGroup;
        int correct = 0;
        System.out.println("请根据单词的假名解释含义: ");
        long startTime = System.currentTimeMillis();
        while (group > 0) {
            int rIndex = (int) (Math.random() * len);
            JpWord word = contains.get(rIndex);
            System.out.println(word.getWord());
            String ans = scanner.next();
            if (word.getMean().contains(ans)) {
                correct++;
                System.out.println("正确");
            } else {
                System.out.println("错误，正确答案是：" + word.getMean());
            }
            group--;
        }
        long finishTime = System.currentTimeMillis();

        int cRate = correct * 100 / rGroup;
        int sec = (int) (finishTime - startTime) / 1000;
        System.out.println("本组练习一共" + rGroup + "组，用时:" + sec + "秒, 回答正确率为" + cRate + "%.");
    }

    private static void doWordToMean() {
        int len = contains.size();
        int rGroup = (int) (Math.random() * len);
        int group = rGroup;
        int correct = 0;
        System.out.println("请根据单词解释含义: ");
        long startTime = System.currentTimeMillis();
        while (group > 0) {
            int rIndex = (int) (Math.random() * len);
            JpWord word = contains.get(rIndex);

            String hWord = word.gethWord();
            if (hWord == null || hWord.trim().isEmpty()) {
                hWord = word.getWord();
            }
            System.out.println(hWord);
            String ans = scanner.next();
            if (word.getMean().contains(ans)) {
                correct++;
                System.out.println("正确");
            } else {
                System.out.println("错误，正确答案是：" + word.getMean());
            }
            group--;
        }
        long finishTime = System.currentTimeMillis();

        int cRate = correct * 100 / rGroup;
        int sec = (int) (finishTime - startTime) / 1000;
        System.out.println("本组练习一共" + rGroup + "组，用时:" + sec + "秒, 回答正确率为" + cRate + "%.");
    }

    private static void doMeanToWuShi() {
        int len = contains.size();
        int rGroup = (int) (Math.random() * len);
        int group = rGroup;
        int correct = 0;
        System.out.println("请根据单词的含义填写假名: ");
        long startTime = System.currentTimeMillis();
        while (group > 0) {
            int rIndex = (int) (Math.random() * len);
            JpWord word = contains.get(rIndex);
            System.out.println(word.getMean());
            String ans = scanner.next();
            if (word.getWord().equals(ans)) {
                correct++;
                System.out.println("正确");
            } else {
                System.out.println("错误，正确答案是：" + word.getWord());
            }
            group--;
        }
        long finishTime = System.currentTimeMillis();

        int cRate = correct * 100 / rGroup;
        int sec = (int) (finishTime - startTime) / 1000;
        System.out.println("本组练习一共" + rGroup + "组，用时:" + sec + "秒, 回答正确率为" + cRate + "%.");
    }

    private static void doMeanToWord() {
        int len = contains.size();
        int rGroup = (int) (Math.random() * len);
        int group = rGroup;
        int correct = 0;
        System.out.println("请根据单词的含义填写单词: ");
        long startTime = System.currentTimeMillis();
        while (group > 0) {
            int rIndex = (int) (Math.random() * len);
            JpWord word = contains.get(rIndex);
            System.out.println(word.getMean());
            String ans = scanner.next();
            if (word.gethWord() != null && word.gethWord().equals(ans)) {
                correct++;
                System.out.println("正确");
            } else if (word.gethWord() == null || word.gethWord().trim().isEmpty()) {
                if (word.gethWord().equals(ans)) {
                    correct++;
                    System.out.println("正确");
                } else {
                    System.out.println("错误，正确答案是：" + word.getWord());
                }
            } else {
                System.out.println("错误，正确答案是：" + word.gethWord());
            }
            group--;
        }
        long finishTime = System.currentTimeMillis();

        int cRate = correct * 100 / rGroup;
        int sec = (int) (finishTime - startTime) / 1000;
        System.out.println("本组练习一共" + rGroup + "组，用时:" + sec + "秒, 回答正确率为" + cRate + "%.");
    }

    // 初始化单词数据
    private static void init(int fNumber,int cNumber) {

        String fileName = "words" + fNumber;
        File file = new File(FileUtil.getWordPath() + fileName);

        if (file.exists() && file.isFile()) {
            // 读取文件内容
            int chapter = -1;
            boolean dataFlag = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (CheckUtil.checkLine(line)) {
                        // 获取章节号
                        chapter = FileUtil.getChapter(line);
                        if (chapter == cNumber) {
                            dataFlag = true;
                            continue;
                        }
                        // 已经到了下一章了
                        if (chapter == cNumber + 1) {
                            break;
                        }
                        if (dataFlag) {
                            contains.add(simpleInitWord(line));
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("文件读取失败！");
                System.exit(0);
            }
        } else {
            System.out.println("单词文件不存在！");
            System.exit(0);
        }
    }

    private static JpWord simpleInitWord(String data) {
        String[] splits = data.split("-");
        return new JpWord(splits[0], splits[1], splits[2]);
    }

}
