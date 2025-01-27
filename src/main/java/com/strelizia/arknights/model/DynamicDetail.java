package com.strelizia.arknights.model;

import java.util.List;

/**
 * @author wangzy
 * @Date 2021/1/12 18:07
 **/
public class DynamicDetail {
    private String name;
    private String type;
    private String title;
    private String text;
    private String picUrl;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
