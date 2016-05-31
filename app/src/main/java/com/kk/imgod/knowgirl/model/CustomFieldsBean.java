package com.kk.imgod.knowgirl.model;

import com.kk.imgod.knowgirl.customerclass.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;

public class CustomFieldsBean extends RealmObject {
    private RealmList<RealmString> thumb_c;

    public RealmList<RealmString> getThumb_c() {
        return thumb_c;
    }

    public void setThumb_c(RealmList<RealmString> thumb_c) {
        this.thumb_c = thumb_c;
    }
}