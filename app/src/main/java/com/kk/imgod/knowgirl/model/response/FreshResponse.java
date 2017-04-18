package com.kk.imgod.knowgirl.model.response;

import com.kk.imgod.knowgirl.model.FreshBean;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.model
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-17 14:00
 * 修改人：gaokang
 * 修改时间：2016-05-17 14:00
 * 修改备注：
 */
public class FreshResponse extends RealmObject {

    /**
     * status : ok
     * count : 24
     * count_total : 55153
     * pages : 2299
     * posts : [{"id":78861,"url":"http://jandan.net/2016/05/17/a-secret-meeting.html","title":"科学家们正在密谋人类基因组合成","date":"2016-05-17 11:30:33","tags":[{"id":449,"slug":"%e8%b5%b0%e8%bf%9b%e7%a7%91%e5%ad%a6","title":"走进科学","description":"","post_count":3371}],"author":{"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""},"comment_count":6,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/FSMI.jpg"]}},{"id":78872,"url":"http://jandan.net/2016/05/17/visited-place.html","title":"全球被访问次数最多的地点\u2014\u2014零岛","date":"2016-05-17 10:30:28","tags":[{"id":120,"slug":"geek","title":"Geek","description":"","post_count":3310}],"author":{"id":587,"slug":"cedric","name":"Cedric","first_name":"Cedric","last_name":"Hsu","nickname":"Cedric","url":"http://weibo.com/u/1857631950","description":""},"comment_count":15,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/BBBD.jpg"]}},{"id":78878,"url":"http://jandan.net/2016/05/17/uncontacted-island.html","title":"从未接触过现代社会的最原始部落","date":"2016-05-17 09:30:21","tags":[{"id":672,"slug":"%e6%97%85%e6%b8%b8","title":"旅游","description":"","post_count":288}],"author":{"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""},"comment_count":19,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/VKPE.jpg"]}},{"id":78881,"url":"http://jandan.net/2016/05/17/telephone-salad.html","title":"致富信息：电话亭里卖快餐","date":"2016-05-17 08:00:19","tags":[{"id":698,"slug":"%e8%87%b4%e5%af%8c%e4%bf%a1%e6%81%af","title":"致富信息","description":"","post_count":929}],"author":{"id":482,"slug":"luga","name":"luga","first_name":"","last_name":"","nickname":"luga","url":"","description":""},"comment_count":26,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/TKYF.jpg"]}},{"id":78890,"url":"http://jandan.net/2016/05/17/your-electronics.html","title":"数据惊人：家里那些「关掉」的电器，耗电占居民总电量消费的1/4","date":"2016-05-17 00:01:48","tags":[{"id":575,"slug":"%e7%8e%af%e4%bf%9d","title":"环保","description":"","post_count":665}],"author":{"id":608,"slug":"solo","name":"SOLO","first_name":"","last_name":"","nickname":"SOLO","url":"","description":""},"comment_count":41,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/RA0H.jpg"]}},{"id":78886,"url":"http://jandan.net/2016/05/16/get-past-that.html","title":"[Quora] 我经历过的最困难的事","date":"2016-05-16 22:58:30","tags":[{"id":816,"slug":"quora","title":"Quora","description":"","post_count":288}],"author":{"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""},"comment_count":36,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/IY2T.jpg"]}},{"id":78883,"url":"http://jandan.net/2016/05/16/macromolecule-viruses.html","title":"IBM制造出可以消灭所有病毒的高分子","date":"2016-05-16 22:00:22","tags":[{"id":731,"slug":"%e9%ab%98%e7%a7%91%e6%8a%80","title":"高科技","description":"","post_count":802}],"author":{"id":591,"slug":"west","name":"west","first_name":"","last_name":"","nickname":"west","url":"","description":""},"comment_count":32,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/N1H3.jpg"]}},{"id":78875,"url":"http://jandan.net/2016/05/16/your-vibrator.html","title":"如何把你的振动棒带过机场安检","date":"2016-05-16 21:00:56","tags":[{"id":549,"slug":"sex","title":"SEX","description":"","post_count":1287}],"author":{"id":587,"slug":"cedric","name":"Cedric","first_name":"Cedric","last_name":"Hsu","nickname":"Cedric","url":"http://weibo.com/u/1857631950","description":""},"comment_count":25,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/0NTV.jpg"]}},{"id":78879,"url":"http://jandan.net/2016/05/16/space-the-week-2.html","title":"一周天文图回顾","date":"2016-05-16 20:00:33","tags":[{"id":683,"slug":"%e5%a4%a9%e6%96%87","title":"天文","description":"","post_count":388}],"author":{"id":601,"slug":"lee","name":"Lee","first_name":"","last_name":"","nickname":"Lee","url":"","description":""},"comment_count":12,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/TDNA.jpg"]}},{"id":78835,"url":"http://jandan.net/2016/05/16/scalp-bacteria.html","title":"细菌才是对抗头皮屑的关键","date":"2016-05-16 19:00:33","tags":[{"id":831,"slug":"%e5%81%a5%e5%ba%b7","title":"健康","description":"","post_count":814}],"author":{"id":524,"slug":"vertebrae","name":"Vertebrae","first_name":"","last_name":"","nickname":"Vertebrae","url":"","description":""},"comment_count":22,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/XR11.jpg"]}},{"id":78868,"url":"http://jandan.net/2016/05/16/chested-public.html","title":"君子坦荡荡：一位美国女士的露乳行动","date":"2016-05-16 17:52:33","tags":[{"id":561,"slug":"%e5%a5%b3%e6%80%a7","title":"女性","description":"","post_count":534}],"author":{"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""},"comment_count":51,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/17VE.jpg"]}},{"id":78866,"url":"http://jandan.net/2016/05/16/so-bloody-hot.html","title":"谜之真相：火辣的英国男士","date":"2016-05-16 17:00:14","tags":[{"id":120,"slug":"geek","title":"Geek","description":"","post_count":3310}],"author":{"id":587,"slug":"cedric","name":"Cedric","first_name":"Cedric","last_name":"Hsu","nickname":"Cedric","url":"http://weibo.com/u/1857631950","description":""},"comment_count":37,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/Y23D.jpg"]}},{"id":78871,"url":"http://jandan.net/2016/05/16/the-worlds-plant.html","title":"地球上1/5的植物正濒临灭绝","date":"2016-05-16 16:00:52","tags":[{"id":449,"slug":"%e8%b5%b0%e8%bf%9b%e7%a7%91%e5%ad%a6","title":"走进科学","description":"","post_count":3370}],"author":{"id":593,"slug":"banana","name":"一只咸鱼","first_name":"","last_name":"","nickname":"一只咸鱼","url":"","description":""},"comment_count":11,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/DV54.jpg"]}},{"id":78874,"url":"http://jandan.net/2016/05/16/big-bang-problems.html","title":"关于大爆炸理论的3个问题","date":"2016-05-16 15:09:54","tags":[{"id":683,"slug":"%e5%a4%a9%e6%96%87","title":"天文","description":"","post_count":388}],"author":{"id":608,"slug":"solo","name":"SOLO","first_name":"","last_name":"","nickname":"SOLO","url":"","description":""},"comment_count":46,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/OXAQ.jpg"]}},{"id":78856,"url":"http://jandan.net/2016/05/16/middle-class.html","title":"美国城市的中产阶层阵容正在萎缩","date":"2016-05-16 15:00:52","tags":[{"id":836,"slug":"%e7%bb%8f%e6%b5%8e","title":"经济","description":"","post_count":98}],"author":{"id":399,"slug":"shixinxin","name":"shixinxin","first_name":"xinxin","last_name":"shi","nickname":"shixinxin","url":"","description":""},"comment_count":22,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/E0UX.jpg"]}},{"id":78865,"url":"http://jandan.net/2016/05/16/elephantiasis-symptoms.html","title":"是什么导致了象皮病 [慎入]","date":"2016-05-16 14:00:31","tags":[{"id":831,"slug":"%e5%81%a5%e5%ba%b7","title":"健康","description":"","post_count":814}],"author":{"id":607,"slug":"vc","name":"VC","first_name":"","last_name":"","nickname":"VC","url":"","description":""},"comment_count":19,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/OJ7E.jpg"]}},{"id":78855,"url":"http://jandan.net/2016/05/16/zika-how.html","title":"寨卡病毒会终结人类吗？","date":"2016-05-16 13:14:50","tags":[{"id":668,"slug":"%e5%ae%89%e5%85%a8%e8%ad%a6%e7%a4%ba","title":"安全警示","description":"","post_count":306}],"author":{"id":605,"slug":"paw","name":"卤鸡爪子","first_name":"","last_name":"","nickname":"卤鸡爪子","url":"","description":""},"comment_count":17,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/PMLH.jpg"]}},{"id":78860,"url":"http://jandan.net/2016/05/16/162-mensa-iq.html","title":"10岁小女孩智商162超爱因斯坦","date":"2016-05-16 12:00:40","tags":[{"id":120,"slug":"geek","title":"Geek","description":"","post_count":3310}],"author":{"id":482,"slug":"luga","name":"luga","first_name":"","last_name":"","nickname":"luga","url":"","description":""},"comment_count":65,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/MNMZ.jpg"]}},{"id":78847,"url":"http://jandan.net/2016/05/16/magnetoshell.html","title":"NASA新项目：在火星上建磁力场和深度睡眠舱","date":"2016-05-16 11:00:16","tags":[{"id":706,"slug":"%e7%81%ab%e6%98%9f","title":"火星","description":"","post_count":130}],"author":{"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""},"comment_count":9,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/3RTR.jpg"]}},{"id":78844,"url":"http://jandan.net/2016/05/16/acted-like-ghost.html","title":"17岁印度女孩装鬼吓走骚扰者","date":"2016-05-16 09:30:38","tags":[{"id":854,"slug":"%e5%8d%b0%e5%ba%a6","title":"印度","description":"","post_count":271}],"author":{"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""},"comment_count":29,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/3Z9Y.jpg"]}},{"id":78842,"url":"http://jandan.net/2016/05/16/human-hamster.html","title":"为给医院筹款，猛男自制「仓鼠轮」并将连跑24小时","date":"2016-05-16 08:00:12","tags":[{"id":554,"slug":"%e7%9c%9f%e7%9a%84%e7%8c%9b%e5%a3%ab","title":"真的猛士","description":"","post_count":875}],"author":{"id":482,"slug":"luga","name":"luga","first_name":"","last_name":"","nickname":"luga","url":"","description":""},"comment_count":24,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/KFC0.jpg"]}},{"id":78845,"url":"http://jandan.net/2016/05/16/weekend-55.html","title":"一周优评：看爱情片的时候，会散发出单身狗的清香","date":"2016-05-16 00:29:39","tags":[{"id":650,"slug":"%e5%91%a8%e6%9c%ab%e5%95%a6","title":"周末啦","description":"","post_count":254}],"author":{"id":578,"slug":"skydom","name":"金星","first_name":"","last_name":"","nickname":"金星","url":"","description":""},"comment_count":22,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/XWLQ.jpg"]}},{"id":78830,"url":"http://jandan.net/2016/05/16/birth-control-2.html","title":"避孕大师：19种避孕方式及优缺点","date":"2016-05-16 00:24:45","tags":[{"id":549,"slug":"sex","title":"SEX","description":"","post_count":1287}],"author":{"id":601,"slug":"lee","name":"Lee","first_name":"","last_name":"","nickname":"Lee","url":"","description":""},"comment_count":47,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/0FJR.jpg"]}},{"id":78813,"url":"http://jandan.net/2016/05/15/hahahaha-4.html","title":"无聊图大吐槽【40P】","date":"2016-05-15 23:01:27","tags":[{"id":663,"slug":"%e5%a4%a7%e5%90%90%e6%a7%bd","title":"大吐槽","description":"","post_count":347}],"author":{"id":578,"slug":"skydom","name":"金星","first_name":"","last_name":"","nickname":"金星","url":"","description":""},"comment_count":30,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/28UP.jpg"]}}]
     */

    private String status;
    private int count;
    private int count_total;
    private int pages;
    private RealmList<FreshBean> posts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<FreshBean> getPosts() {
        return posts;
    }

    public void setPosts(RealmList<FreshBean> posts) {
        this.posts = posts;
    }
}
