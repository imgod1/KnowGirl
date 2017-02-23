package com.kk.imgod.knowgirl.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.model
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-16 16:23
 * 修改人：gaokang
 * 修改时间：2016-05-16 16:23
 * 修改备注：
 */
public class ZhihuResponse extends RealmObject{
    @PrimaryKey
    private String date;
    private RealmList<ZhihuStory> stories;
    private RealmList<ZhihuStory> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RealmList<ZhihuStory> getStories() {
        return stories;
    }

    public void setStories(RealmList<ZhihuStory> stories) {
        this.stories = stories;
    }

    public RealmList<ZhihuStory> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(RealmList<ZhihuStory> top_stories) {
        this.top_stories = top_stories;
    }
}
