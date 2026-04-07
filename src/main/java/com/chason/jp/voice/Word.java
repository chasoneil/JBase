package com.chason.jp.voice;

public class Word {

    private String id;

    private String mean;

    private String word;

    private String voice;

    public String getMean() {
        return mean;
    }

    public String getId() {
        return id;
    }

    public String getVoice() {
        return voice;
    }

    public void setMean(String word) {
        this.mean = word;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
