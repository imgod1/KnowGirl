package com.kk.imgod.knowgirl.model;

import java.io.Serializable;

/**
 * 项目名称：KnowGirl
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/2/28 14:51
 * 修改人：gk
 * 修改时间：2017/2/28 14:51
 * 修改备注：
 */
public class GifBean implements Serializable {
    private int id;
    private String gif_url;
    private String gif_content;
    private int group_id;
    private int real_id;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getReal_id() {
        return real_id;
    }

    public void setReal_id(int real_id) {
        this.real_id = real_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGif_url() {
        return gif_url;
    }

    public void setGif_url(String gif_url) {
        this.gif_url = gif_url;
    }

    public String getGif_content() {
        return gif_content;
    }

    public void setGif_content(String gif_content) {
        this.gif_content = gif_content;
    }
}
