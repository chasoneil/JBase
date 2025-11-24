package com.chason.jp.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Jp练习中的对话
 */
public class Dialog {

    private String title;

    // 对话是一组一组的
    private List<List<String>> dialogs;

    public String getTitle() {
        return title;
    }

    public List<List<String>> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<List<String>> dialogs) {
        this.dialogs = dialogs;
    }

    public Dialog(String title) {
        this.title = title;
        this.dialogs = new ArrayList<>();
    }
}
