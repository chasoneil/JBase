package com.chason.jp.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 单句
 */
public class Single {

    // 这个单句练习的名称
    private String title;

    private List<String> sentences;

    public Single (String title) {
        this.title = title;
        this.sentences = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }
}
