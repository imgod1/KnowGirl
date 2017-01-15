package com.kk.imgod.knowgirl.model;

/**
 * Created by imgod on 2017/1/15.
 */

public class AppVersion {

    /**
     * versionCode : 1
     * versionName : 1.0
     * apkUrl : apk下载地址
     * info : 更新介绍
     */

    private int versionCode;
    private String versionName;
    private String apkUrl;
    private String info;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
