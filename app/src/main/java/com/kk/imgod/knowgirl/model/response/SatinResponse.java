package com.kk.imgod.knowgirl.model.response;

import com.kk.imgod.knowgirl.model.MetadataBean;
import com.kk.imgod.knowgirl.model.SatinBean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 项目名称：KnowGirl
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/2/22 15:37
 * 修改人：gk
 * 修改时间：2017/2/22 15:37
 * 修改备注：
 */
public class SatinResponse extends RealmObject {

    /**
     * total : 217780
     * max_pos : 14877490966
     */
    @PrimaryKey
    private int id;
    private MetadataBean metadata;

    private RealmList<SatinBean> data;

    public RealmList<SatinBean> getData() {
        return data;
    }

    public void setData(RealmList<SatinBean> data) {
        this.data = data;
    }

}
