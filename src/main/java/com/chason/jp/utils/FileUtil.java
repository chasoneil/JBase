package com.chason.jp.utils;

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

}
