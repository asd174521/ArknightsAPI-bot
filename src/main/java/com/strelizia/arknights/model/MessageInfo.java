package com.strelizia.arknights.model;

/**
 * @author wangzy
 * @Date 2020/12/10 16:37
 **/
public class MessageInfo {
    private String text;
    private Long qq;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }
}