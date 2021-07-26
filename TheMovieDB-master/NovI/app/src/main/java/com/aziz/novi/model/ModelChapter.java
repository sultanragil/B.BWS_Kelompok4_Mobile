package com.aziz.novi.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

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
