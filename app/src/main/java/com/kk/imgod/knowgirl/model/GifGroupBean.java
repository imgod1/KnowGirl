package com.kk.imgod.knowgirl.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 项目名称：KnowGirl
 * 类描述：gif组model
 * 创建人：gk
 * 创建时间：2017/2/27 17:47
 * 修改人：gk
 * 修改时间：2017/2/27 17:47
 * 修改备注：
 */
public class GifGroupBean extends RealmObject {

    /**
     * gallery_id : 132208
     * title : 全球搞笑GIF图第1781弹：这就是传说中的神打？
     * cover_url : http://s1.dwstatic.com/group1/M00/AD/20/ad206ffae987b1dd55fb85be4a4285a4698.jpg
     * updated : 2017-02-17
     * comment_count : 1395
     * url : http://tu.duowan.com/gallery/132208.html
     */

    @PrimaryKey
    private String gallery_id;
    private String title;
    private String cover_url;
    private String updated;
    private String comment_count;
    private String url;
    private int img_width;
    private int img_height;
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


    public String getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(String gallery_id) {
        this.gallery_id = gallery_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
