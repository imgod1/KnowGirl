package com.kk.imgod.knowgirl.model.response;

import com.kk.imgod.knowgirl.model.GankGrilBean;

import java.util.List;

/**
 * 项目名称：KnowGirl
 * 类描述：gank 妹子图返回结果
 * 创建人：gk
 * 创建时间：2017/4/18 15:38
 * 修改人：gk
 * 修改时间：2017/4/18 15:38
 * 修改备注：
 */
public class GankGirlResponse {
    private boolean error;
    private List<GankGrilBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankGrilBean> getResults() {
        return results;
    }

    public void setResults(List<GankGrilBean> results) {
        this.results = results;
    }
}
