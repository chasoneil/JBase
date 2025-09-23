package com.chason.jp.wushi;

import java.util.*;

public class JpBaseUtils {

    private static List<WuShi> contains;

    private static int correct = 0;

    private static Scanner sc = new Scanner(System.in);

    private static long startTime;
    private static long finishTime;

    public static void main(String[] args) {
        start();
    }

    // todo 加入循环选择的机制
    private static void start() {

        correct = 0;
        initData();
        System.out.println("开始进行练习,请输入练习模式：1、单一的平片假名练习; 2、随机的平片假名组合练习;");
        String s1 = sc.next();
        int s1i = checkInput(s1);
        switch (s1i) {
            case 1:
                singleTest();
                break;
            case 2:
                comboTest();
                break;
            default:
                System.out.println("您输入的选项不存在，测试结束。");
                System.exit(-1);
        }
    }

    private static void singleTest() {
        System.out.println();
        System.out.println("开始进行平片假名的单个练习,请选择练习类型：");
        System.out.println("1、根据平假名写片假名; 2、根据片假名写平假名;");
        String s2 = sc.next();
        int s2i = checkInput(s2);

        if (s2i == 1 || s2i == 2) {
            doSingleTest(s2i);
        } else {
            System.out.println("您输入的选项不存在，测试结束。");
            System.exit(-1);
        }
    }

    private static void doSingleTest(int type) {
        int testTime = (int)(Math.random() * 5) + 5;
        int timeCopy = testTime;

        if (type == 1) {
            System.out.println("请根据平假名写出对应的片假名,按回车结束:");
        } else if (type == 2) {
            System.out.println("请根据平假名写出对应的片假名,按回车结束:");
        }

        startTime = System.currentTimeMillis();
        while (testTime > 0) {
            if (type == 1) {
                doPingToPian();
            } else if (type == 2) {
                doPianToPing();
            }
            testTime--;
        }
        finishTime = System.currentTimeMillis();

        int cRate = correct * 100 / timeCopy;
        int sec = (int) (finishTime - startTime) / 1000;
        System.out.println("本组练习一共" + timeCopy + "组，用时:" + sec + "秒, 回答正确率为" + cRate + "%.");
    }

    private static void doPingToPian() {
        int randomIndex = (int) (Math.random() * contains.size());
        WuShi w = contains.get(randomIndex);
        System.out.println(w.ping);
        String input = sc.next();
        if (input.equals(w.pian)) {
            System.out.println("正确");
            correct++;
        } else {
            System.out.println("错误");
        }
    }

    private static void doPianToPing() {
        int randomIndex = (int) (Math.random() * contains.size());
        WuShi w = contains.get(randomIndex);
        System.out.println(w.pian);
        String input = sc.next();
        if (input.equals(w.ping)) {
            System.out.println("正确");
            correct++;
        } else {
            System.out.println("错误");
        }
    }

    private static void comboTest() {

        System.out.println();
        System.out.println("开始进行平片假名的组合练习,请选择练习类型：");
        System.out.println("1、根据平假名组合写片假名; 2、根据片假名组合写平假名;");

        String s2 = sc.next();
        int s2i = checkInput(s2);

        if (s2i == 1 || s2i == 2) {
            doComboTest(s2i);
        } else {
            System.out.println("您输入的选项不存在，测试结束。");
            System.exit(-1);
        }
    }

    private static void doComboTest(int type) {

        int testTime = (int)(Math.random() * 5) + 5;  // 测试的组数也是5-10组
        int timeCopy = testTime;

        if (type == 1) {
            System.out.println("请根据平假名写出对应的片假名,按回车结束:");
        } else if (type == 2) {
            System.out.println("请根据片假名写出对应的平假名,按回车结束:");
        }

        startTime = System.currentTimeMillis();
        while (testTime > 0) {
            int rWords = (int)(Math.random() * 3) + 3;    // 每个单词的字数
            if (type == 1) {
                doPingToPianCombo(rWords);
            } else if (type == 2) {
                doPianToPingCombo(rWords);
            }
            testTime--;
        }

        finishTime = System.currentTimeMillis();

        int sec = (int) (finishTime - startTime) / 1000;
        int cRate = correct * 100 / timeCopy;
        System.out.println("本组练习一共" + timeCopy + "组，用时:" + sec + "秒, 回答正确率为" + cRate + "%.");
    }

    // 平假名 -> 片假名的组合练习
    private static void doPingToPianCombo(int words) {

        int len = contains.size();
        List<Integer> selected = new ArrayList<>();
        while (words > 0) {
            int s = (int) (Math.random() * len);
            selected.add(s);
            words--;
        }

        // 被选中词的index已经存放在selected中
        StringBuilder sbQ = new StringBuilder();
        StringBuilder sbA = new StringBuilder();
        for (Integer i : selected) {
            WuShi w = contains.get(i);
            sbQ.append(w.ping);
            sbA.append(w.pian);
        }

        System.out.println(sbQ.toString());
        String res = sc.next();
        if (res.contentEquals(sbA)) {
            System.out.println("正确");
            correct++;
        } else {
            System.out.println("错误");
        }

    }

    private static void doPianToPingCombo(int words) {
        int len = contains.size();
        List<Integer> selected = new ArrayList<>();
        while (words > 0) {
            int s = (int) (Math.random() * len);
            selected.add(s);
            words--;
        }

        // 被选中词的index已经存放在selected中
        StringBuilder sbQ = new StringBuilder();
        StringBuilder sbA = new StringBuilder();
        for (Integer i : selected) {
            WuShi w = contains.get(i);
            sbQ.append(w.pian);
            sbA.append(w.ping);
        }

        System.out.println(sbQ.toString());
        String res = sc.next();
        if (res.contentEquals(sbA)) {
            System.out.println("正确");
            correct++;
        } else {
            System.out.println("错误");
        }
    }

    private static int checkInput(String input) {

        int selectNumber = -1;
        try {
            selectNumber = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("您只能输入提示的选项数字，然后按下回车");
            System.out.println("本次练习结束，请重新开始运行练习程序。");
            System.exit(-1);
        }

        return selectNumber;
    }


    static class WuShi {

        String ping;
        String pian;
        String roma;

        public WuShi (String ping, String pian, String roma) {
            this.ping = ping;
            this.pian = pian;
            this.roma = roma;
        }
    }

    // 初始化数据
    private static void initData() {

        contains = new ArrayList<>();

        WuShi a = new WuShi("あ", "ア", "a");
        WuShi i = new WuShi("い", "イ", "i");
        WuShi u = new WuShi("う", "ウ", "u");
        WuShi e = new WuShi("え", "エ", "e");
        WuShi o = new WuShi("お", "オ", "o");

        WuShi ka = new WuShi("か", "カ", "ka");
        WuShi ki = new WuShi("き", "キ", "ki");
        WuShi ku = new WuShi("く", "ク", "ku");
        WuShi ke = new WuShi("け", "ケ", "ke");
        WuShi ko = new WuShi("こ", "コ", "ko");

        WuShi ga = new WuShi("が", "ガ", "ga");
        WuShi gi = new WuShi("ぎ", "ギ", "gi");
        WuShi gu = new WuShi("ぐ", "グ", "gu");
        WuShi ge = new WuShi("げ", "ゲ", "ge");
        WuShi go = new WuShi("ご", "ゴ", "go");

        WuShi ya = new WuShi("や", "ヤ", "ya");
        WuShi yu = new WuShi("ゆ", "ユ", "yu");
        WuShi yo = new WuShi("よ", "ヨ", "yo");

        // 拗音
        WuShi kya = new WuShi("きゃ", "キャ", "kya");
        WuShi kyu = new WuShi("きゅ", "キュ", "kyu");
        WuShi kyo = new WuShi("きょ", "キョ", "kyo");
        WuShi gya = new WuShi("ぎゃ", "ギャ", "gya");
        WuShi gyu = new WuShi("ぎゅ", "ギュ", "gyu");
        WuShi gyo = new WuShi("ぎょ", "ギョ", "gyo");

        contains.add(a);
        contains.add(i);
        contains.add(u);
        contains.add(e);
        contains.add(o);

        contains.add(ka);
        contains.add(ki);
        contains.add(ku);
        contains.add(ke);
        contains.add(ko);

        contains.add(ga);
        contains.add(gi);
        contains.add(gu);
        contains.add(ge);
        contains.add(go);

        contains.add(ya);
        contains.add(yu);
        contains.add(yo);

        contains.add(kya);
        contains.add(kyu);
        contains.add(kyo);
        contains.add(gya);
        contains.add(gyu);
        contains.add(gyo);
    }
}
