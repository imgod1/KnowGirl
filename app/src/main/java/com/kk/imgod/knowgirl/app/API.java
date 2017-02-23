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

    public static final String DBMEIZI_BASE_URL = "http://www.dbmeinv.com/dbgroup/show.htm?cid=";//4&pager_offset=1
    public static final int DBMEIZI_TYPE_BIG_BREAST = 2;//大奶
    public static final int DBMEIZI_TYPE_LEG = 3;//美腿
    public static final int DBMEIZI_TYPE_NICE = 4;//颜值
    public static final int DBMEIZI_TYPE_QIPA = 5;//混合
    public static final int DBMEIZI_TYPE_HIP = 6;//翘臀
    public static final int DBMEIZI_TYPE_STOCK = 7;//丝袜
    //开屏图片获取
    public static final String BOOHEE_WELCOME_IMG = "http://bingo.boohee.com/api/v1/home/items?app_device=Android&user_key=14db31ef-872a-42e1-8ef5-86e55eb1e3ff&token=m4nqA5aNR1d7zovzMZKz&app_version=5.5.4&app_key=one&channel=tencent&phone_model=Coolpad+8297&os_version=4.4.2";
    //检查版本更新的url
    public static final String CHECK_APP_VERSION_URL = "http://ojt0l04lb.bkt.clouddn.com/knowirl_version.json";
    //检查版本下载的url 需要在后面追加app名字 渠道号 以及版本号
    public static final String DOWNLOAD_APP_VERSION_URL = "http://ojt0l04lb.bkt.clouddn.com/";

    /**
     * 段子URL
     */
    public static final String SATIN_URL = "http://napi.uc.cn/3/classes/topic/lists/%E6%AE%B5%E5%AD%90?_app_id=hottopic&_size=10&_fetch=1&_fetch_incrs=1&_fetch_total=1&_select=like_start%2Cdislike_start%2Ctitle%2Ctag%2Cmedia_data%2Clist_info%2Ccontent%2Cavatar%2Cuser_name%2Cis_hot%2Chot_comment%2Ccomment_total%2Coriginal%2Ctv_duration&_max_pos=";
}
