package com.kk.imgod.knowgirl.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MetadataBean extends RealmObject {
    private int total;
    @PrimaryKey
    private long max_pos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getMax_pos() {
        return max_pos;
    }

    public void setMax_pos(long max_pos) {
        this.max_pos = max_pos;
    }
}