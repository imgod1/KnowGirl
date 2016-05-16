package com.kk.imgod.knowgirl.model;


import java.util.List;

/**
 * zhihu news item in list
 */
public class ZhihuStory {

    /**
     * images : ["http://pic1.zhimg.com/aef18b16a9a6dcb445d5c235784c25a8.jpg"]
     * type : 0
     * id : 7813824
     * ga_prefix : 012915
     * title : 运气好的话，说不定 3 万年就把木星挪过来
     */
    private int type;
    private String title;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ZhihuStory() {
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