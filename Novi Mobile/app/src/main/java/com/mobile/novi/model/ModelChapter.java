package com.mobile.novi.model;

import java.io.Serializable;



public class ModelChapter implements Serializable {

    private String id;
    private String chapter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
}
