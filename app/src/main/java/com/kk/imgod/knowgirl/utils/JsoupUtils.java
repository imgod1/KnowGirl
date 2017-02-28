package com.kk.imgod.knowgirl.utils;

import com.kk.imgod.knowgirl.model.GifBean;
import com.kk.imgod.knowgirl.model.ImageBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：KnowGirl
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/1/12 15:48
 * 修改人：gk
 * 修改时间：2017/1/12 15:48
 * 修改备注：
 */
public class JsoupUtils {

    /**
     * 从html网页中得到想要的图片Bean集合数据
     *
     * @param html 网页数据
     * @param cid  图片类别
     * @return 包含图片Bean的一个集合
     */
    public static List<ImageBean> getImgBeanListFromHtml(String html, String cid) {
        List<String> imgUrlList = getImgUrlListFromHtml(html);
        List<String> imgIdList = getImgIdListFromHtml(html);
        List<ImageBean> imageBeanList = new ArrayList<>();
        for (int i = 0; i < imgUrlList.size(); i++) {
            ImageBean imageBean = new ImageBean();
            imageBean.setImg(imgUrlList.get(i));
            imageBean.setCid(cid);
            imageBean.setId(Integer.parseInt(imgIdList.get(i)));
            imageBeanList.add(imageBean);
        }
        return imageBeanList;
    }

    /**
     * 从html网页中得到想要的图片集合数据
     *
     * @param html 网页数据
     * @return 包含图片链接的一个集合
     */
    public static List<String> getImgUrlListFromHtml(String html) {
        List<String> list = new ArrayList();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.thumbnail>div.img_single>a>img");
        for (int i = 0; i < elements.size(); i++) {
            String imgUrl = elements.get(i).attr("src");
            list.add(imgUrl);
        }
        return list;
    }

    /**
     * 从html网页中得到想要的图片指向连接的集合数据
     *
     * @param html 网页数据
     * @return 包含图片链接的一个集合
     */
    public static List<String> getImgIdListFromHtml(String html) {
        List<String> list = new ArrayList();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.thumbnail>div.img_single>a");
        for (int i = 0; i < elements.size(); i++) {
            String imgUrl = elements.get(i).attr("href");
            //http://www.dbmeinv.com/dbgroup/1019629,从中拿到1019629
            int startPosition = imgUrl.lastIndexOf("/");
            String subString = imgUrl.substring(startPosition + 1);
            list.add(subString);
        }
        return list;
    }

    //------------------------------关于Gif的-----------------------------------------
    public static List<GifBean> getGifBeanListFromHtml(String html) {
        List<String> imgUrlList = getGifUrlListFromHtml(html);
        List<String> imgTextList = getGifTextListFromHtml(html);
        List<GifBean> imageBeanList = getGifListFromHtml(html);
        for (int i = 0; i < imageBeanList.size(); i++) {
            GifBean gifBean = imageBeanList.get(i);
            gifBean.setGif_url(imgUrlList.get(i));
            gifBean.setGif_content(imgTextList.get(i));
        }
        return imageBeanList;
    }

    /**
     * 从html网页中得到想要的图片集合数据
     *
     * @param html 网页数据
     * @return 包含图片链接的一个集合
     */
    public static List<String> getGifUrlListFromHtml(String html) {
        List<String> list = new ArrayList();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.pic-box>a>img");
        for (int i = 0; i < elements.size(); i++) {
            String imgUrl = elements.get(i).attr("src");
            Lg.e("test", "getGifUrlListFromHtml:" + imgUrl);
            list.add(imgUrl);
        }
        return list;
    }

    /**
     * 从html网页中得到想要的图片内容说明文字
     *
     * @param html 网页数据
     * @return 包含图片内容的集合
     */
    public static List<String> getGifTextListFromHtml(String html) {
        List<String> list = new ArrayList();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.pic-box>p.comment");
        for (int i = 0; i < elements.size(); i++) {
            String content = elements.get(i).text();
            Lg.e("test", "getGifTextListFromHtml:" + content);
            list.add(content);
        }
        return list;
    }

    /**
     * 从html网页中得到想要的图片指向连接的集合数据
     *
     * @param html 网页数据
     * @return 包含图片链接的一个集合
     */
    public static List<GifBean> getGifListFromHtml(String html) {
        List<GifBean> list = new ArrayList();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.pic-box>a");
        for (int i = 0; i < elements.size(); i++) {
            String imgUrl = elements.get(i).attr("href");
            //http://tu.duowan.com/gallery/131802.html#p3 从中分别拿到131802 和 3
            int startPosition = imgUrl.lastIndexOf("/");
            int endPosition = imgUrl.lastIndexOf(".html");
            String groupId = imgUrl.substring(startPosition + 1, endPosition);
            Lg.e("test", "getGifListFromHtml:group id:" + groupId);

            startPosition = imgUrl.lastIndexOf("p");
            String id = imgUrl.substring(startPosition + 1);
            Lg.e("test", "getGifListFromHtml: id:" + id);
            GifBean gifBean = new GifBean();
            gifBean.setReal_id(Integer.parseInt(id));
            gifBean.setGroup_id(Integer.parseInt(groupId));

            list.add(gifBean);
        }
        return list;
    }
}
