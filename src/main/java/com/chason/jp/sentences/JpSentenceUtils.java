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

    public static void main(String[] args) {
        start();
    }

    /*
        1. 扫描当前文件夹下所有可以选择的课程
     */
    private static void start() {
        Set<String> lessons = getLessons();

        System.out.println("请选择课程，可以选择的课程有:");
        for (String s: lessons) {
            System.out.println(s);
        }
        String lessonNameInput = sc.next();
        System.out.println("你选择的课程是:" + lessonNameInput);
        System.out.println("初始化课程数据...");
        init(lessonNameInput);
        System.out.println("初始化完成，开始练习");



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

    private static void printLesson(Set<String> lessons) {


    }

    // 初始化该课程的所有数据
    private static void init(String lessonName) {

        String key = null;
        int type = 0;
        boolean ends = false;       // 对话结束标识

        String filePath = HOME_PATH + lessonName + ".txt";
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {

                if (line.startsWith("T")) {
                    key = line.substring(2);
                    initSingle(key);
                    initDialog(key);
                    ends = true;
                    continue;
                }

                if (line.startsWith("单句")) {
                    type = 1;
                    ends = true;
                    continue;
                }

                if (line.startsWith("对话")) {
                    type = 2;
                    // 以对话开始，则没有结束
                    ends = false;
                    continue;
                }

                if (type == 1) {
                    Single single = jpSentence.getSingle(key);
                    if (single == null) {
                        throw new RuntimeException("课程:" + key + "的单句练习不存在");
                    }
                    single.getSentences().add(line);
                    continue;
                }

                if (type == 2 && !ends) {
                    Dialog dialog = jpSentence.getDialog(key);
                    if (dialog == null) {
                        throw new RuntimeException("课程:" + key + "的对话练习不存在");
                    }



                    continue;
                }

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    private static void initSingle(String key) {
        Single single = jpSentence.getSingle(key);
        if (single == null) {
            jpSentence.getSingles().add(new Single(key));
        }
    }

    private static void initDialog(String key) {
        Dialog dialog = jpSentence.getDialog(key);
        if (dialog == null) {
            jpSentence.getDialogs().add(new Dialog(key));
        }
    }
}
