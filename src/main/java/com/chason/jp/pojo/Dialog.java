package com.chason.jp.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Jp练习中的对话
 */
public class Dialog {

    private String title;

    private List<String> content;

    public Dialog(String title) {
        this.title = title;
        this.content = new ArrayList<>();
    }

    public Dialog (String title, List<String> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
