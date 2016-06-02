package com.kk.imgod.knowgirl.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PostBean extends RealmObject {
    @PrimaryKey
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}