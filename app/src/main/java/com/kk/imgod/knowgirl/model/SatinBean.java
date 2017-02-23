package com.kk.imgod.knowgirl.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 项目名称：KnowGirl
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/2/22 15:38
 * 修改人：gk
 * 修改时间：2017/2/22 15:38
 * 修改备注：
 */
public class SatinBean extends RealmObject {

    /**
     * user_name : 宅宅的蛋糕
     * dislike_start : 1
     * _updated_at : 2015-03-27T11:11:18.000+0800
     * _incrs : {"like":142,"share":45,"dislike":110}
     * _created_at : 2014-11-03T16:11:03.000+0800
     * _pos : 5500146079
     * avatar : http://image.uc.cn/s/uae/g/0q/avatar/46.png
     * tag : 段子
     * _id : aaaffa5526d08565bad0e3b81bef234f
     * title : 男人色果然是不分年龄的
     * like_start : 12
     * content : 就刚刚，一大爷怎么也得五六十了，大庭广众得摸了一女的胸就跑，利落的不行，一看就是老手！等众人反应过来，早跑得无影儿了
     */

    private String user_name;
    private int dislike_start;
    private String _updated_at;
    /**
     * like : 142
     * share : 45
     * dislike : 110
     */

//    private IncrsBean _incrs;
    private String _created_at;
    private long _pos;
    private String avatar;
    private String tag;
    @PrimaryKey
    private String _id;
    private String title;
    private int like_start;
    private String content;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getDislike_start() {
        return dislike_start;
    }

    public void setDislike_start(int dislike_start) {
        this.dislike_start = dislike_start;
    }

    public String get_updated_at() {
        return _updated_at;
    }

    public void set_updated_at(String _updated_at) {
        this._updated_at = _updated_at;
    }


    public String get_created_at() {
        return _created_at;
    }

    public void set_created_at(String _created_at) {
        this._created_at = _created_at;
    }

    public long get_pos() {
        return _pos;
    }

    public void set_pos(long _pos) {
        this._pos = _pos;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLike_start() {
        return like_start;
    }

    public void setLike_start(int like_start) {
        this.like_start = like_start;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class IncrsBean {
        private int like;
        private int share;
        private int dislike;

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getDislike() {
            return dislike;
        }

        public void setDislike(int dislike) {
            this.dislike = dislike;
        }
    }
}
