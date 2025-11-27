package com.chason.jp.pojo;

/**
 * 单句
 */
public class Single {

    // 这个单句练习的名称
    private String title;

    private String content;

    public Single (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
