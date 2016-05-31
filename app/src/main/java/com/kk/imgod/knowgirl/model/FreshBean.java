package com.kk.imgod.knowgirl.model;

import com.kk.imgod.knowgirl.customerclass.RealmString;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.model
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-17 13:41
 * 修改人：gaokang
 * 修改时间：2016-05-17 13:41
 * 修改备注：
 */
public class FreshBean extends RealmObject {

    /**
     * id : 78861
     * url : http://jandan.net/2016/05/17/a-secret-meeting.html
     * title : 科学家们正在密谋人类基因组合成
     * date : 2016-05-17 11:30:33
     * tags : [{"id":449,"slug":"%e8%b5%b0%e8%bf%9b%e7%a7%91%e5%ad%a6","title":"走进科学","description":"","post_count":3371}]
     * author : {"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""}
     * comment_count : 6
     * custom_fields : {"thumb_c":["http://tankr.net/s/custom/FSMI.jpg"]}
     */

    private int id;
    private String url;
    private String title;
    @PrimaryKey
    private String date;
    /**
     * id : 523
     * slug : joan
     * name : 肌肉桃
     * first_name :
     * last_name :
     * nickname : 肌肉桃
     * url :
     * description :
     */
    @Ignore
    private AuthorBean author;
    private int comment_count;
    private CustomFieldsBean custom_fields;
    /**
     * id : 449
     * slug : %e8%b5%b0%e8%bf%9b%e7%a7%91%e5%ad%a6
     * title : 走进科学
     * description :
     * post_count : 3371
     */
    @Ignore
    private List<TagsBean> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public CustomFieldsBean getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(CustomFieldsBean custom_fields) {
        this.custom_fields = custom_fields;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class AuthorBean implements Serializable {
        private int id;
        private String slug;
        private String name;
        private String first_name;
        private String last_name;
        private String nickname;
        private String url;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class TagsBean implements Serializable {
        private int id;
        private String slug;
        private String title;
        private String description;
        private int post_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPost_count() {
            return post_count;
        }

        public void setPost_count(int post_count) {
            this.post_count = post_count;
        }
    }
}
