package com.kk.imgod.knowgirl.model;


import com.kk.imgod.knowgirl.customerclass.RealmString;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * zhihu news item in list
 */
public class ZhihuStory extends RealmObject{


    /**
     * images : ["http://pic1.zhimg.com/027e1e60a53cebe7dea457178870d774.jpg"]
     * type : 0
     * id : 8310664
     * ga_prefix : 051621
     * title : 要说烧脑的时空穿越，这些电影都不应该错过
     */


    @PrimaryKey
    private int id;
    private int type;
    private String ga_prefix;
    private String title;
    private RealmList<RealmString> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<RealmString> getImages() {
        return images;
    }

    public void setImages(RealmList<RealmString> images) {
        this.images = images;
    }
}