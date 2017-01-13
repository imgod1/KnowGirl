package com.kk.imgod.knowgirl.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by imgod on 2016/4/25.
 */
public class ImageBean extends RealmObject {

    @PrimaryKey
    private int id;
    private String img;
    private String title;
    private String cid;


    private int img_width;
    private int img_height;

    public ImageBean() {
    }

    public ImageBean(int id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getImg_width() {
        return img_width;
    }

    public void setImg_width(int img_width) {
        this.img_width = img_width;
    }

    public int getImg_height() {
        return img_height;
    }

    public void setImg_height(int img_height) {
        this.img_height = img_height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
