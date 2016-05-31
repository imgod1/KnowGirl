package com.kk.imgod.knowgirl.utils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.utils
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-31 14:34
 * 修改人：gaokang
 * 修改时间：2016-05-31 14:34
 * 修改备注：
 */
public class DBUtils {
    //保存或者更新数据
    public static void copyOrUpdateRealm(Realm realm, RealmObject realmObject) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObject);
        realm.commitTransaction();
    }

    public static <T extends RealmObject> void saveList(Realm realm, List<T> realmObjects) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObjects);
        realm.commitTransaction();
    }
}
