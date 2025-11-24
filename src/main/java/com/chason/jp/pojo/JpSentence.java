package com.chason.jp.pojo;

import java.util.ArrayList;
import java.util.List;

public class JpSentence {

    // 单句 key 练习类型 value 该类型下的所有单句
    private List<Single> singles;

    // 对话
    private List<Dialog> dialogs;

    public List<Single> getSingles() {
        if (this.singles == null)
            singles =  new ArrayList<>();
        return singles;
    }

    public Single getSingle(String key) {
        for (Single s: getSingles()) {
            if (key.equals(s.getTitle())) {
                return s;
            }
        }
        return null;
    }

    public Dialog getDialog(String key) {
        for (Dialog d: getDialogs()) {
            if (key.equals(d.getTitle()))
                return d;
        }
        return null;
    }

    public void setSingles(List<Single> singles) {
        this.singles = singles;
    }

    public List<Dialog> getDialogs() {
        if (dialogs == null)
            dialogs = new ArrayList<>();
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }
}
