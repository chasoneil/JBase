package com.chason.jp.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Jp练习中的对话
 */
public class Dialog {

    private String title;

    // 对话是一组一组的
    private List<DContent> dialogs;

    public String getTitle() {
        return title;
    }

    public List<DContent> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<DContent> dialogs) {
        this.dialogs = dialogs;
    }

    public Dialog(String title) {
        this.title = title;
        this.dialogs = new ArrayList<>();
    }

    public Dialog(String title, String name, String content) {
        this.title = title;
        this.dialogs = new ArrayList<>();
        DContent dContent = new DContent();
        dContent.name = name;
        dContent.content = content;
        dialogs.add(dContent);
    }

    static class DContent {
        String name;
        String content;
    }
}
