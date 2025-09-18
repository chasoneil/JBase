package com.chason.test;

import java.util.*;

public class JpBaseUtils {

    private static List<WuShi> contains;

    private static int correct = 0;

    public static void main(String[] args) {

        start();

    }

    // todo 加入循环选择的机制
    private static void start() {

        correct = 0;
        initData();
        System.out.println("开始进行练习,请输入练习模式：1、单一的平片假名练习; 2、随机的平片假名组合练习;");
        Scanner sc = new Scanner(System.in);
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

    }

    private static void comboTest() {

        System.out.println();
        System.out.println("开始进行平片假名的组合练习,请选择练习类型：");
        System.out.println("1、根据平假名组合写片假名; 2、根据片假名组合写平假名;");

        Scanner sc = new Scanner(System.in);
        String s2 = sc.next();
        int s2i = checkInput(s2);

        switch (s2i) {
            case 1:
                pingToPianCombo();
                break;
            case 2:
                pianToPingCombo();
                break;
            default:
                System.out.println("您输入的选项不存在，测试结束。");
                System.exit(-1);
        }

    }

    private static void pingToPianCombo() {

        int rTest = (int)(Math.random() * 3) + 1;  // 测试每组的数量是5-10
        int rGroup = (int)(Math.random() * 3) + 1; // 测试的组数也是5-10组

        while (rGroup > 0) {
            doPingToPianCombo(rTest);
            rGroup--;
        }

        int cRate = correct * 100 / rGroup;
        System.out.println("您的本组测试已经完成,一共" + rGroup + "组测试,您回答正确了" + correct + "组, 您的正确率为:" + cRate + "%, 请再接再厉!");
    }

    private static void doPingToPianCombo(int rTest) {

        int len = contains.size();
        Set<Integer> selected = new HashSet<>();

        while (rTest > 0) {
            int s = (int) (Math.random() * len);
            while (selected.contains(s)) {
                s = nextSelectIndex(len, s);
            }
            selected.add(s);
            rTest--;
        }

        // 被选中词的index已经存放在selected中
        StringBuilder sbQ = new StringBuilder();
        StringBuilder sbA = new StringBuilder();
        for (Integer i : selected) {
            WuShi w = contains.get(i);
            sbQ.append(w.ping);
            sbA.append(w.pian);
        }

        System.out.println("请根据平假名写出对应的片假名,按回车结束:" + sbQ.toString());
        Scanner sc = new Scanner(System.in);

        String res = sc.next();
        if (res.equals(sbA.toString())) {
            System.out.println("正确");
            correct++;
        } else {
            System.out.println("错误");
        }

    }

    private static int nextSelectIndex(int len, int s) {

        if (s == len-1) {
            return 0;
        }

        return s+1;
    }



    private static void pianToPingCombo() {

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
    }


}
