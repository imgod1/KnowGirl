package com.kk.imgod.knowgirl.app;

/**
 * Created by imgod on 2016/4/25.
 */
public class API {
    //Zhihu API
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/news/";
    public static final String ZHIHU_NEWS_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static final String ZHIHU_NEWS_BEFORE = "http://news-at.zhihu.com/api/4/news/before/";

    //Fresh things API
    public static final String JIANDAN_FRESH_NEWS = "http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&page=";
    public static final String JIANDAN_FRESH_NEWS_DETAIL = "http://i.jandan.net/?oxwlxojflwblxbsapi=get_post&include=content&id=";

    /**
     * {"description":"……","id":1,"keywords":"……","name":"性感美女","seq":1,"title":"……"},
     {"description":"……","id":2,"keywords":"……","name":"韩日美女","seq":2,"title":"……"},
     {"description":"……","id":3,"keywords":"……","name":"丝袜美腿","seq":3,"title":"……"},
     {"description":"……","id":4,"keywords":"……","name":"美女照片","seq":4,"title":"……"},
     {"description":"……","id":5,"keywords":"……","name":"美女写真","seq":5,"title":"……"},
     {"description":"……","id":6,"keywords":"……","name":"清纯美女","seq":6,"title":"……"},
     {"description":"……","id":2,"keywords":"……","name":"性感车模","seq":7,"title":"……"}
     */
    //图片接口url,page=1&rows=20&id=2
    public static final String PICTURE_URL = "http://www.tngou.net/tnfs/api/list?";

    public static final String SEX_GIRL_URL = "http://www.tngou.net/tnfs/api/list?rows=10&id=1&page=";
    public static final String JAPAN_GIRL_URL = "http://www.tngou.net/tnfs/api/list?rows=10&id=2&page=";
    public static final String STOCK_GIRL_URL = "http://www.tngou.net/tnfs/api/list?rows=10&id=3&page=";
    public static final String PHOTO_GIRL_URL = "http://www.tngou.net/tnfs/api/list?rows=10&id=4&page=";
    public static final String WRITE_REAL_GIRL_URL = "http://www.tngou.net/tnfs/api/list?rows=10&id=5&page=";
    public static final String PURE_GIRL_URL = "http://www.tngou.net/tnfs/api/list?rows=10&id=6&page=";
    public static final String SEX_CAR_GIRL_URL = "http://www.tngou.net/tnfs/api/list?rows=10&id=7&page=";

    //图片资源的基地址
    public static final String PICTURE_BASE_URL = "http://tnfs.tngou.net/image";


    //开屏图片获取
    public static final String BOOHEE_WELCOME_IMG = "http://bingo.boohee.com/api/v1/home/items?app_device=Android&user_key=14db31ef-872a-42e1-8ef5-86e55eb1e3ff&token=m4nqA5aNR1d7zovzMZKz&app_version=5.5.4&app_key=one&channel=tencent&phone_model=Coolpad+8297&os_version=4.4.2";
}
