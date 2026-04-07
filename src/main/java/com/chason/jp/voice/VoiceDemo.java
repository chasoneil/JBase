package com.chason.jp.voice;

import com.chason.algorithm.utils.StringUtils;
import com.chason.jp.utils.FileUtil;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.util.*;

public class VoiceDemo {

    private static Map<String, Word> contains = new HashMap<>();

    private static Scanner sc = new Scanner(System.in);

    private static List<Integer> classNos = new ArrayList<>();

    static {
        classNos.add(25);
        classNos.add(26);
        classNos.add(27);
    }

    public static void main(String[] args) throws Exception{
        start();
    }

    private static void start() {

        System.out.println("开始进行单词练习,请选择练习类型：1-听录音翻译成中文 2-根据中文翻译成日文");
        int execType = sc.nextInt();
        if (execType != 1 && execType != 2) {
            System.out.println("练习类型不支持");
            System.exit(0);
        }

        System.out.println("请选择练习的课程数：当前支持：（25,26,27）");
        int classNumber = sc.nextInt();
        if (!classNos.contains(classNumber)) {
            System.out.println("课程不存在");
            System.exit(0);
        }

        init(classNumber);

        if (execType == 1) {
            System.out.println("听录音，写出单词的中文含义：");
            doExec1();
            System.out.println("练习结束");
        }

        if (execType == 2) {
            System.out.println("根据中文含义写出日文单词：");
            doExec2();
        }

    }

    private static void doExec2() {
        for(String key: contains.keySet()) {
            Word word = contains.get(key);
            System.out.println("中文：" + word.getMean());
            String result = sc.next();
            if (checkWord(result, word)) {
                System.out.println("回答正确");
            } else {
                System.out.println("回答错误，正确答案：" + word.getWord());
            }
        }
    }

    private static void doExec1() {

        for(String key: contains.keySet()) {
            Word word = contains.get(key);
            play(word.getVoice());
            String result = sc.next();
            if (checkVoice(result, word)) {
                System.out.println("回答正确");
            } else {
                System.out.println("回答错误，正确答案：" + word.getMean());
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean checkVoice(String result, Word word) {
        String res = word.getMean();
        String[] means = res.split(",");
        boolean flag = false;
        for (String mean : means) {
            if (mean.equals(result)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private static boolean checkWord(String result, Word word) {
        String res = word.getWord();
        boolean flag = false;
        if (result.equals(res)) {
            flag = true;
        }
        return flag;
    }


    private static void init(int classNumber) {
        System.out.println("开始初始化数据");
        contains.clear();
        String base = System.getProperty("user.dir");

        String wordPath = base + "/data/word/" + classNumber;
        String voicePath = base + "/data/voice/" + classNumber + "/";

        try (BufferedReader reader = new BufferedReader(new FileReader(wordPath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                String[] msgs = line.split(" ");
                Word word = new Word();
                word.setId(msgs[0]);
                word.setWord(msgs[1]);
                word.setMean(msgs[2]);
                contains.put(word.getId(), word);
            }
        } catch (IOException e) {
            System.out.println("文件读取失败！");
            System.exit(0);
        }

        File[] mp3s = new File(voicePath).listFiles((path,name) -> name.toLowerCase().endsWith(".mp3"));
        for (File f : mp3s) {
            String id = f.getName().replace(".mp3", "");
            if (contains.get(id) == null) {
                System.err.println("未登记的词汇：" + f.getName());
                continue;
            }

            Word word = contains.get(id);
            word.setVoice(f.getAbsolutePath());
        }
        System.out.println("初始化数据完成");
    }

    private static void randomPlay(String dir) throws Exception {

        List<File> mp3s = FileUtil.getMp3Count(dir);
        if (mp3s.isEmpty()) {
            System.out.println("未找到MP3文件音源");
            System.exit(0);
        }

        while (!mp3s.isEmpty()) {
            int rIndex = (int) (Math.random() * mp3s.size());
            File mp3 = mp3s.get(rIndex);
            play(mp3.getAbsolutePath());
            System.out.println("下一个");
            Thread.sleep(1000);
            mp3s.remove(mp3);
        }

        System.out.println("结束");
    }

    private static void play(String mp3) {
        try (FileInputStream fis = new FileInputStream(mp3)) {
            Player player = new Player(fis);
            player.play();
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
