package com.chason.jp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {

    private static final String WORD_PATH = "src/main/java/com/chason/jp/words/";

    public static String getWordPath() {
        return WORD_PATH;
    }

    public static int getChapter(String line) {

        if (line.startsWith("--")) {
            String[] words = line.split(" ");
            int chapter = -1;
            try {
                chapter = Integer.parseInt(words[1]);
                return chapter;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }

    public static List<File> getMp3Count(String dir) {

        File floder = new File(dir);
        if (!floder.exists() || !floder.isDirectory()) {
            throw new RuntimeException("未找到音频文件");
        }

        File[] mp3s = floder.listFiles((path,name) -> name.toLowerCase().endsWith(".mp3"));
        List<File> res = new ArrayList<>();
        Collections.addAll(res, mp3s);
        return res;
    }

}
