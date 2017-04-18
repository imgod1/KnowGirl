package com.kk.imgod.knowgirl.model.response;

import com.kk.imgod.knowgirl.model.ImageBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imgod on 2016/4/25.
 */
public class ImageResponse implements Serializable {
    private boolean status;
    private int total;
    private List<ImageBean> tngou;

    public List<ImageBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<ImageBean> tngou) {
        this.tngou = tngou;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isStatus() {

        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
