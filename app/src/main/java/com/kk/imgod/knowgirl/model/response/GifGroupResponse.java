package com.kk.imgod.knowgirl.model.response;

import com.kk.imgod.knowgirl.model.GifGroupBean;

import java.util.List;

/**
 * 项目名称：KnowGirl
 * 类描述：gif请求返回值
 * 创建人：gk
 * 创建时间：2017/2/28 10:30
 * 修改人：gk
 * 修改时间：2017/2/28 10:30
 * 修改备注：
 */
public class GifGroupResponse {

    /**
     * hasMore : 1
     * offset : 50
     * gallerys : [{"gallery_id":"131492","title":"全球搞笑GIF图第1751弹：没有老婆就自己做一个！","cover_url":"http://s1.dwstatic.com/group1/M00/A6/43/a643386d08052ed185899e0ad2ef56e66020.jpg","updated":"2017-01-12","comment_count":"910","url":"http://tu.duowan.com/gallery/131492.html"},{"gallery_id":"131469","title":"全球搞笑GIF图第1750弹：老婆说要刻苦练习骑乘位，但总觉得练的方式有点不太对","cover_url":"http://s1.dwstatic.com/group1/M00/29/57/2957ce66eebd8ab60bf6d40643c86eaa4323.jpg","updated":"2017-01-11","comment_count":"846","url":"http://tu.duowan.com/gallery/131469.html"},{"gallery_id":"131434","title":"全球搞笑GIF图第1749弹：我当你是朋友，你居然只想跟我打炮","cover_url":"http://s1.dwstatic.com/group1/M00/18/E7/18e7dcdb26e5e7efb6dd5c0fefd7991e713.jpg","updated":"2017-01-10","comment_count":"949","url":"http://tu.duowan.com/gallery/131434.html"},{"gallery_id":"131402","title":"全球搞笑GIF图第1748弹：嗯\u2026\u2026你这是乳腺增生了，必须每天按摩","cover_url":"http://s1.dwstatic.com/group1/M00/21/D2/21d2ca5fafeebf7ea17a9c10e8d44dbf5479.jpg","updated":"2017-01-09","comment_count":"817","url":"http://tu.duowan.com/gallery/131402.html"},{"gallery_id":"131389","title":"全球搞笑GIF图第1747弹：阿三的防御能力真的是厉害啊","cover_url":"http://s1.dwstatic.com/group1/M00/E4/F0/d44974917ebd98e5b561930186744b5c.gif","updated":"2017-01-08","comment_count":"694","url":"http://tu.duowan.com/gallery/131389.html"},{"gallery_id":"131375","title":"全球搞笑GIF图第1746弹：这是什么车太豪华了","cover_url":"http://s1.dwstatic.com/group1/M00/48/CA/2d5cd0aae66aa51e134b69818bf02b69.gif","updated":"2017-01-07","comment_count":"684","url":"http://tu.duowan.com/gallery/131375.html"},{"gallery_id":"131366","title":"全球搞笑GIF图第1745弹：爱莉你能不能敬业点，投入点？","cover_url":"http://s1.dwstatic.com/group1/M00/2E/57/2e575b5ad2b45b39517514ee43a49ec97678.jpg","updated":"2017-01-06","comment_count":"950","url":"http://tu.duowan.com/gallery/131366.html"},{"gallery_id":"131334","title":"全球搞笑GIF图第1744弹：30包夜肯不肯？不肯拉倒","cover_url":"http://s1.dwstatic.com/group1/M00/87/45/8745974ef43e0816aae20521a20d15a01953.jpg","updated":"2017-01-05","comment_count":"1284","url":"http://tu.duowan.com/gallery/131334.html"},{"gallery_id":"131302","title":"全球搞笑GIF图第1743弹：接招吧妖孽！我的第二把枪要走火了","cover_url":"http://s1.dwstatic.com/group1/M00/9D/3F/9d3f71bfeeee1332c894b97c780121628026.jpg","updated":"2017-01-04","comment_count":"916","url":"http://tu.duowan.com/gallery/131302.html"},{"gallery_id":"131276","title":"全球搞笑GIF图第1742弹：被封面欺骗下载了整整三天，打开居然是这玩意","cover_url":"http://s1.dwstatic.com/group1/M00/FE/87/fe872b467f0162113a909a764e3308f19576.jpg","updated":"2017-01-03","comment_count":"833","url":"http://tu.duowan.com/gallery/131276.html"}]
     */

    private int hasMore;
    private int offset;
    /**
     * gallery_id : 131492
     * title : 全球搞笑GIF图第1751弹：没有老婆就自己做一个！
     * cover_url : http://s1.dwstatic.com/group1/M00/A6/43/a643386d08052ed185899e0ad2ef56e66020.jpg
     * updated : 2017-01-12
     * comment_count : 910
     * url : http://tu.duowan.com/gallery/131492.html
     */

    private List<GifGroupBean> gallerys;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<GifGroupBean> getGallerys() {
        return gallerys;
    }

    public void setGallerys(List<GifGroupBean> gallerys) {
        this.gallerys = gallerys;
    }

}
