package com.chason.jp.wushi;

import com.chason.jp.pojo.WuShi;
import com.chason.jp.utils.CheckUtil;

import java.util.*;

public class JpBaseUtils {

    private static List<WuShi> contains;
    private static int correct = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    // todo 加入循环选择的机制
    private static void start() {

        correct = 0;
        initData();
        System.out.println("开始进行练习,请输入练习模式：1、单一的平片假名练习; 2、随机的平片假名组合练习;");
        String s1 = sc.next();
        int s1i = CheckUtil.checkInput(s1);
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
        int s2i = CheckUtil.checkInput(s2);

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

        long startTime = System.currentTimeMillis();
        while (testTime > 0) {
            if (type == 1) {
                doPingToPian();
            } else if (type == 2) {
                doPianToPing();
            }
            testTime--;
        }
        long finishTime = System.currentTimeMillis();

        int cRate = correct * 100 / timeCopy;
        int sec = (int) (finishTime - startTime) / 1000;
        System.out.println("本组练习一共" + timeCopy + "组，用时:" + sec + "秒, 回答正确率为" + cRate + "%.");
    }

    private static void doPingToPian() {
        int randomIndex = (int) (Math.random() * contains.size());
        WuShi w = contains.get(randomIndex);
        System.out.println(w.getPing());
        String input = sc.next();
        if (input.equals(w.getPian())) {
            System.out.println("正确");
            correct++;
        } else {
            System.out.println("错误");
        }
    }

    private static void doPianToPing() {
        int randomIndex = (int) (Math.random() * contains.size());
        WuShi w = contains.get(randomIndex);
        System.out.println(w.getPian());
        String input = sc.next();
        if (input.equals(w.getPing())) {
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
        int s2i = CheckUtil.checkInput(s2);

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

        long startTime = System.currentTimeMillis();
        while (testTime > 0) {
            int rWords = (int)(Math.random() * 3) + 3;    // 每个单词的字数
            if (type == 1) {
                doPingToPianCombo(rWords);
            } else if (type == 2) {
                doPianToPingCombo(rWords);
            }
            testTime--;
        }

        long finishTime = System.currentTimeMillis();

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
            sbQ.append(w.getPing());
            sbA.append(w.getPian());
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
            sbQ.append(w.getPian());
            sbA.append(w.getPing());
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

        WuShi sa = new WuShi("さ", "サ", "sa");
        WuShi si = new WuShi("し", "シ", "shi;si");  // 读音类似西瓜的西
        WuShi su = new WuShi("す", "ス", "su");      // 读音类似 思考的思 后面带一点点的u
        WuShi se = new WuShi("せ", "セ", "se");
        WuShi so = new WuShi("そ", "ソ", "so");

        WuShi za = new WuShi("ざ", "ザ", "za");
        WuShi zi = new WuShi("じ", "ジ", "ji");
        WuShi zu = new WuShi("ず", "ズ", "zu");    // 读音类似 滋味的滋
        WuShi ze = new WuShi("ぜ", "ゼ", "ze");
        WuShi zo = new WuShi("ぞ", "ゾ", "zo");

        WuShi ta = new WuShi("た", "タ", "ta");
        WuShi ti = new WuShi("ち", "チ", "chi;ti");
        WuShi tu = new WuShi("つ", "ツ", "tsu;tu");
        WuShi te = new WuShi("て", "テ", "te");
        WuShi to = new WuShi("と", "ト", "to");

        WuShi da = new WuShi("だ", "ダ", "da");
        WuShi ji = new WuShi("ぢ", "ヂ", "di");   // 但是读音是 ji 读音同 じ
        WuShi du = new WuShi("づ", "ヅ", "du");   // 读音也是 zi　读音同 ず
        WuShi de = new WuShi("で", "デ", "de");
        WuShi do_ = new WuShi("ど", "ド", "do");

        WuShi na = new WuShi("な", "ナ", "na");
        WuShi ni = new WuShi("に", "ニ", "ni");
        WuShi nu = new WuShi("ぬ", "ヌ", "nu");
        WuShi ne = new WuShi("ね", "ネ", "ne");
        WuShi no = new WuShi("の", "ノ", "no");

        WuShi ya = new WuShi("や", "ヤ", "ya");
        WuShi yu = new WuShi("ゆ", "ユ", "yu");       // 读音是 优
        WuShi yo = new WuShi("よ", "ヨ", "yo");

        // 拗音
        WuShi kya = new WuShi("きゃ", "キャ", "kya");
        WuShi kyu = new WuShi("きゅ", "キュ", "kyu");
        WuShi kyo = new WuShi("きょ", "キョ", "kyo");
        WuShi gya = new WuShi("ぎゃ", "ギャ", "gya");
        WuShi gyu = new WuShi("ぎゅ", "ギュ", "gyu");
        WuShi gyo = new WuShi("ぎょ", "ギョ", "gyo");

        WuShi sya = new WuShi("しゃ", "シャ", "sha;sya");
        WuShi syu = new WuShi("しゅ", "シュ", "shu;syu");
        WuShi syo = new WuShi("しょ", "ショ", "sho;syo");
        WuShi zya = new WuShi("じゃ", "ジャ", "ja;zya");
        WuShi zyu = new WuShi("じゅ", "ジュ", "ju;zyu");
        WuShi zyo = new WuShi("じょ", "ジョ", "jo;zyo");

        // 因为本行的拗音和上一行的拗音一样，所以只有一组拗音
        WuShi qya = new WuShi("ちゃ", "チャ", "cha;qya");
        WuShi qyu = new WuShi("ちゅ", "チュ", "chu;qyu");
        WuShi qyo = new WuShi("ちょ", "チョ", "cho;qyo");

        WuShi nya = new WuShi("にゃ", "ニャ", "nya");
        WuShi nyu = new WuShi("にゅ", "ニュ", "nyu");
        WuShi nyo = new WuShi("にょ", "ニョ", "nyo");

        WuShi n = new WuShi("ん", "ン", "nn");

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

        contains.add(sa);
        contains.add(si);
        contains.add(su);
        contains.add(se);
        contains.add(so);

        contains.add(za);
        contains.add(zi);
        contains.add(zu);
        contains.add(ze);
        contains.add(zo);

        contains.add(ta);
        contains.add(ti);
        contains.add(tu);
        contains.add(te);
        contains.add(to);

        contains.add(da);
        contains.add(ji);
        contains.add(du);
        contains.add(de);
        contains.add(do_);

        contains.add(na);
        contains.add(ni);
        contains.add(nu);
        contains.add(ne);
        contains.add(no);

        contains.add(ya);
        contains.add(yu);
        contains.add(yo);

        contains.add(kya);
        contains.add(kyu);
        contains.add(kyo);
        contains.add(gya);
        contains.add(gyu);
        contains.add(gyo);

        contains.add(sya);
        contains.add(syu);
        contains.add(syo);
        contains.add(zya);
        contains.add(zyu);
        contains.add(zyo);

        contains.add(qya);
        contains.add(qyu);
        contains.add(qyo);

        contains.add(nya);
        contains.add(nyu);
        contains.add(nyo);

        contains.add(n);
    }
}
