package com.chason.jp.sentences;

import com.chason.algorithm.utils.StringUtils;
import com.chason.jp.pojo.Dialog;
import com.chason.jp.pojo.JpSentence;
import com.chason.jp.pojo.Single;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JpSentenceUtils {

    private static Scanner sc = new Scanner(System.in);

    private static JpSentence jpSentence = new JpSentence();

    private static final String HOME_PATH = "./src/main/java/com/chason/jp/sentences";

    private static String userInput = null;

    private static List<String> cache = new ArrayList<>();

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Set<String> lessons = getLessons();

        System.out.println("请选择课程，可以选择的课程有:");
        for (String s: lessons) {
            System.out.println(s);
        }
        userInput = sc.next();
        System.out.println("你选择的课程是:" + userInput);
        System.out.println("初始化课程数据...");
        init(userInput);
        System.out.println("初始化完成，开始练习");

        practice();
    }

    private static void practice() {

        List<Single> singles = jpSentence.getSingles();
        // 将list中的顺序随机打乱
        System.out.println("请根据中文翻译成日语：");
        Collections.shuffle(singles);
        for (Single s : singles) {
            System.out.println("[" + s.getTitle() + "]");
            System.out.println(s.getContent().split("-")[0]);
            userInput = sc.next();
            // 将用户输入的所有句子中的空格去掉
            userInput = userInput.replaceAll("\\p{Space}+", "");
            if (s.getContent().split("-")[1].equals(userInput)) {
                System.out.println("回答正确");
            } else {
                System.out.println("回答错误,正确答案：" + s.getContent().split("-")[1]);
            }
        }

        List<Dialog> dialogs = jpSentence.getDialogs();
        Collections.shuffle(dialogs);
        for (Dialog d : dialogs) {
            System.out.println("[" + d.getTitle() + "]");
            for (String s : d.getContent()) {
                System.out.println(s.split("-")[0]);
                userInput = sc.next();
                // 将用户输入的所有句子中的空格去掉
                userInput = userInput.replaceAll("\\s+", "");
                if (s.split("-")[1].equals(userInput)) {
                    System.out.println("回答正确");
                } else {
                    System.out.println("回答错误,正确答案：" + s.split("-")[1]);
                }
            }
        }
    }

    private static Set<String> getLessons() {

        Set<String> lessons = new HashSet<>();

        File dir = new File(HOME_PATH);
        File[] files = dir.listFiles();
        for (File f: files) {
            if (f.getName().startsWith("class")) {
                lessons.add(removeSuffix(f.getName()));
            }
        }
        return lessons;
    }

    private static String removeSuffix(String fileName) {

        if (StringUtils.isEmpty(fileName)) {
            return fileName;
        }

        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex != -1 && dotIndex != 0) {
            return fileName.substring(0, dotIndex);
        } else {
            return fileName;
        }
    }

    // 初始化该课程的所有数据
    private static void init(String lessonName) {

        String key = null;
        int type = 0;
        String filePath = HOME_PATH + File.separator +lessonName + ".txt";
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {

                if (StringUtils.isEmpty(line)) {
                    if (!cache.isEmpty()) {
                        flushDialog(key);
                    }
                    continue;
                }

                if (line.startsWith("T")) {
                    key = line.substring(2);
                    type = 0;
                    continue;
                }

                if (line.startsWith("单句") ) {
                    type = 0;
                    continue;
                }

                if (line.startsWith("对话")) {
                    type = 1;
                    continue;
                }

                if (type == 0) {
                    initSingle(key, line);
                    continue;
                }

                // 能到这里说明 当前肯定是对话且不为空行
                cache.add(line);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }


    private static void initSingle(String key, String content) {
        Single single = new Single(key, content);
        jpSentence.getSingles().add(single);
    }

    private static void flushDialog(String key) {
        Dialog dialog = new Dialog(key);
        List<String> content = new ArrayList<>(cache);
        dialog.setContent(content);
        jpSentence.getDialogs().add(dialog);
        cache.clear();
    }
}
