package com.kk.imgod.knowgirl.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by imgod on 2016/4/25.
 */
public class ImageBean extends RealmObject {

    /**
     * count : 840
     * fcount : 0
     * galleryclass : 2
     * id : 716
     * img : /ext/160408/b6938f83adf37b1eeb1c320bd5842b88.jpg
     * rcount : 0
     * size : 23
     * time : 1460114164000
     * title : 清纯女孩松嶋ののか私房诱惑美图
     */

    private int count;
    private int fcount;
    private int galleryclass;
    @PrimaryKey
    private int id;
    private String img;
    private int rcount;
    private int size;
    private long time;
    private String title;

    private int img_width = 1;
    private int img_height = 1;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
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

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
