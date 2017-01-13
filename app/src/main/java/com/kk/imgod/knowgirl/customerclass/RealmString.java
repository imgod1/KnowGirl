package com.kk.imgod.knowgirl.customerclass;

import io.realm.RealmObject;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.customerclass
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-31 11:35
 * 修改人：gaokang
 * 修改时间：2016-05-31 11:35
 * 修改备注：
 */
public class RealmString extends RealmObject{
    private String val;

    public RealmString() {
    }

    public RealmString(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
