package com.kk.imgod.knowgirl.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 项目名称：KnowGirl
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/4/18 15:16
 * 修改人：gk
 * 修改时间：2017/4/18 15:16
 * 修改备注：
 */
public class GankGrilBean extends RealmObject {

    /**
     * _id : 58f3980c421aa9544b773ff1
     * createdAt : 2017-04-17T00:13:00.136Z
     * desc : 4-17
     * publishedAt : 2017-04-17T11:32:14.674Z
     * source : chrome
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-16-17934400_1738549946443321_2924146161843437568_n.jpg
     * used : true
     * who : daimajia
     */

    @PrimaryKey
    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    private int img_width;
    private int img_height;
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
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
}
