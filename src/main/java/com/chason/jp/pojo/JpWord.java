package com.chason.jp.pojo;

import java.util.ArrayList;
import java.util.List;

public class JpWord {

    // 单词的假名
    private String word;

    // 正式的单词(含汉字)
    private String hWord;

    // 罗马音
    private String roma;

    // 单词的含义
    private List<String> mean;

    private String voiceType;

    public JpWord(String word, String hWord, String roma, String mean,String voiceType) {
        this.word = word;
        this.hWord = hWord;
        this.roma = roma;
        this.mean = new ArrayList<>();
        this.voiceType = voiceType;
    }

    public JpWord(String word, String hWord, String mean) {
        this.word = word;
        this.hWord = hWord;
        this.mean = new ArrayList<>();
        if (!mean.isEmpty()) {
            String[] ms = mean.split(";");
            for (String m: ms) {
                this.mean.add(m.trim());
            }
        }
    }

    public void setVoiceType(String voiceType) {
        this.voiceType = voiceType;
    }

    public String getVoiceType() {
        return voiceType;
    }

    public void setMean(List<String> mean) {
        this.mean = mean;
    }

    public List<String> getMean() {
        return mean;
    }

    public void addMean(String ex) {
        this.mean.add(ex);
    }

    public String getWord() {
        return word;
    }

    public String gethWord() {
        return hWord;
    }
}
