package com.kk.imgod.knowgirl.model;


import java.io.Serializable;
import java.util.List;

/**
 * zhihu news item in list
 */
public class ZhihuStory implements Serializable{


    /**
     * images : ["http://pic1.zhimg.com/027e1e60a53cebe7dea457178870d774.jpg"]
     * type : 0
     * id : 8310664
     * ga_prefix : 051621
     * title : 要说烧脑的时空穿越，这些电影都不应该错过
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}