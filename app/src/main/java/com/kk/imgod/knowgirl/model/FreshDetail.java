package com.kk.imgod.knowgirl.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.model
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016-05-17 15:51
 * 修改人：gaokang
 * 修改时间：2016-05-17 15:51
 * 修改备注：
 */
public class FreshDetail extends RealmObject {

    /**
     * status : ok
     * post : {"id":78861,"content":"<p>上周，一百多名科学家、律师以及企业家聚集在一起，探讨合成人类基因组这个激进计划的可能性。奇怪的是，这场会议没有邀请任何媒体出席，且与会者被要求对此事守口如瓶。想到合成人类基因组这一话题的分量，他们这样的行事令人心慌不已。<\/p>\n<p><img src=\"http://tankr.net/s/medium/FSMI.jpg\" alt=\"科学家们正在密谋人类基因组合成\" /><\/p>\n<p>合成人类基因组的概念与基因编辑完全不同。比起科学家们将某段基因拿到其它地方，科学家们合成人类基因组时需要利用化学物质制造人类基因组中的所有DNA。合成基因组与基因修饰完全不同，前者不会采用任何天然基因。相反，它依赖人类定制的碱基对。这为各种可能性打开了方便之门，遗传学家们不再受到自然界中碱基对两两配对的限制。<\/p>\n<p>目前，科学家们将合成基因组当做一种打造全新微生物和动物的方法，同样的原则也适用于人类。也因此它能够不需要任何父母，制造出定制的人类。这种重磅话题需要认真的思考和讨论。可出于某些原因，这一未来项目进行得并不顺利。<\/p>\n<p>《纽约时报》的科技文作者Andrew Pollack表示，哈佛医学院在周二举行了一场秘密会议，探讨合成人类基因组这一话题。与会者严禁接触媒体或者发与该会议有关的推特。<\/p>\n<p>哈佛医学院的遗传学教授George Church是该项目的关键组织者，他表示这完全是一场误会。他说这场会议与合成人类基因组无关，它是为了提升合成长串DNA的可能性，这样遗传学家们才能利用这些创造动物、植物和微生物。<\/p>\n<p>有趣的是，Pollack指出该项目的原始名称是HGP2: 人类基因组合成项目。更重要的是，该会议的邀约曾清楚地表示该会议的首要目标是在十年内于细胞株内合成完整的人类基因组。随后，会议组织者将会议名称改为了HGP-Write：在细胞内测试大型合成基因。他们表示改名原因是因为原本的会议名称只是为了吸引人眼球而已。最奇怪的是，该会议原本就没有邀请媒体，又何来吸引人眼球一说。<\/p>\n<p>至于为何要秘密开会，Church表示这是因为他的团队给某个科学期刊投稿了，在稿件没有发表出来之前他们不能公开讨论稿件内容。这又是一个奇怪的理由，为什么要在稿件得到公布许可之前开会呢？在稿件发表出来之后再开会不是更有意义么？事实上，在禁令制度下，新闻媒体通常被邀请提前阅读科学文献，出于新闻道德和协议，记者们已经习惯对此保持沉默。<\/p>\n<p>上文曾提到Church打算在十年内在某个细胞内打造出完整的人类基因组，这一目标真乃野心勃勃。最近的进展是科学家们已能合成简单的细菌细胞。但打造一个合成人类细胞复杂得多，这一十年时间线看起来不真实，不过至少它能留给我们足够的时间来思考这一项目前景的重要性。<\/p>\n<p>我(原作)已试图联系George Church博士和另外一名与会者，希望能得到准确答复。<\/p>\n<p><em>[<a href=\"http://jandan.net/2016/05/17/a-secret-meeting.html\">肌肉桃<\/a> via <a target=_blank rel=\"external\" href=\"http://gizmodo.com/experts-held-a-secret-meeting-to-consider-building-a-hu-1776538323\">Gizmodo<\/a>]<\/em><\/p>\n<div class=\"share-links\">\n        <a href=\"http://service.weibo.com/share/share.php?appkey=43259970&searchPic=true&url=http%3A%2F%2Fjandan.net%2F2016%2F05%2F17%2Fa-secret-meeting.html&title=%E3%80%90%E7%A7%91%E5%AD%A6%E5%AE%B6%E4%BB%AC%E6%AD%A3%E5%9C%A8%E5%AF%86%E8%B0%8B%E4%BA%BA%E7%B1%BB%E5%9F%BA%E5%9B%A0%E7%BB%84%E5%90%88%E6%88%90%E3%80%91%E5%93%88%E4%BD%9B%E5%8C%BB%E5%AD%A6%E9%99%A2%E5%9C%A8%E5%91%A8%E4%BA%8C%E4%B8%BE%E8%A1%8C%E4%BA%86%E4%B8%80%E5%9C%BA%E7%A7%98%E5%AF%86%E4%BC%9A%E8%AE%AE%EF%BC%8C%E8%BF%99%E5%9C%BA%E4%BC%9A%E8%AE%AE%E6%B2%A1%E6%9C%89%E9%82%80%E8%AF%B7%E4%BB%BB%E4%BD%95%E5%AA%92%E4%BD%93%E5%87%BA%E5%B8%AD%EF%BC%8C%E4%B8%94%E4%B8%8E%E4%BC%9A%E8%80%85%E8%A2%AB%E8%A6%81%E6%B1%82%E5%AF%B9%E6%AD%A4%E4%BA%8B%E5%AE%88%E5%8F%A3%E5%A6%82%E7%93%B6%E3%80%82%40%E7%85%8E%E8%9B%8B%E7%BD%91&pic=http%3A%2F%2Fjandan.net%2Fshare%2F2016%2F05%2Fp-78861.gif&style=number&language=zh_cn&button=pubilish\" class=\"share-link share-link-weibo\" target=\"_blank\">分享到微博<\/a>\n            <a href=\"javascript:;\" class=\"share-link share-link-weixin\"><img src=\"http://jandan.net/share/2016/05/qr-78861.png\">分享到微信<\/a>\n    <\/div>\n<script>\n    $('.share-link-weixin').click(function(){\n        $(this).find('img').toggle();\n    });\n<\/script>\n"}
     * previous_url : http://jandan.net/2016/05/17/visited-place.html
     */

    private String status;
    /**
     * id : 78861
     * content : <p>上周，一百多名科学家、律师以及企业家聚集在一起，探讨合成人类基因组这个激进计划的可能性。奇怪的是，这场会议没有邀请任何媒体出席，且与会者被要求对此事守口如瓶。想到合成人类基因组这一话题的分量，他们这样的行事令人心慌不已。</p>
     <p><img src="http://tankr.net/s/medium/FSMI.jpg" alt="科学家们正在密谋人类基因组合成" /></p>
     <p>合成人类基因组的概念与基因编辑完全不同。比起科学家们将某段基因拿到其它地方，科学家们合成人类基因组时需要利用化学物质制造人类基因组中的所有DNA。合成基因组与基因修饰完全不同，前者不会采用任何天然基因。相反，它依赖人类定制的碱基对。这为各种可能性打开了方便之门，遗传学家们不再受到自然界中碱基对两两配对的限制。</p>
     <p>目前，科学家们将合成基因组当做一种打造全新微生物和动物的方法，同样的原则也适用于人类。也因此它能够不需要任何父母，制造出定制的人类。这种重磅话题需要认真的思考和讨论。可出于某些原因，这一未来项目进行得并不顺利。</p>
     <p>《纽约时报》的科技文作者Andrew Pollack表示，哈佛医学院在周二举行了一场秘密会议，探讨合成人类基因组这一话题。与会者严禁接触媒体或者发与该会议有关的推特。</p>
     <p>哈佛医学院的遗传学教授George Church是该项目的关键组织者，他表示这完全是一场误会。他说这场会议与合成人类基因组无关，它是为了提升合成长串DNA的可能性，这样遗传学家们才能利用这些创造动物、植物和微生物。</p>
     <p>有趣的是，Pollack指出该项目的原始名称是HGP2: 人类基因组合成项目。更重要的是，该会议的邀约曾清楚地表示该会议的首要目标是在十年内于细胞株内合成完整的人类基因组。随后，会议组织者将会议名称改为了HGP-Write：在细胞内测试大型合成基因。他们表示改名原因是因为原本的会议名称只是为了吸引人眼球而已。最奇怪的是，该会议原本就没有邀请媒体，又何来吸引人眼球一说。</p>
     <p>至于为何要秘密开会，Church表示这是因为他的团队给某个科学期刊投稿了，在稿件没有发表出来之前他们不能公开讨论稿件内容。这又是一个奇怪的理由，为什么要在稿件得到公布许可之前开会呢？在稿件发表出来之后再开会不是更有意义么？事实上，在禁令制度下，新闻媒体通常被邀请提前阅读科学文献，出于新闻道德和协议，记者们已经习惯对此保持沉默。</p>
     <p>上文曾提到Church打算在十年内在某个细胞内打造出完整的人类基因组，这一目标真乃野心勃勃。最近的进展是科学家们已能合成简单的细菌细胞。但打造一个合成人类细胞复杂得多，这一十年时间线看起来不真实，不过至少它能留给我们足够的时间来思考这一项目前景的重要性。</p>
     <p>我(原作)已试图联系George Church博士和另外一名与会者，希望能得到准确答复。</p>
     <p><em>[<a href="http://jandan.net/2016/05/17/a-secret-meeting.html">肌肉桃</a> via <a target=_blank rel="external" href="http://gizmodo.com/experts-held-a-secret-meeting-to-consider-building-a-hu-1776538323">Gizmodo</a>]</em></p>
     <div class="share-links">
     <a href="http://service.weibo.com/share/share.php?appkey=43259970&searchPic=true&url=http%3A%2F%2Fjandan.net%2F2016%2F05%2F17%2Fa-secret-meeting.html&title=%E3%80%90%E7%A7%91%E5%AD%A6%E5%AE%B6%E4%BB%AC%E6%AD%A3%E5%9C%A8%E5%AF%86%E8%B0%8B%E4%BA%BA%E7%B1%BB%E5%9F%BA%E5%9B%A0%E7%BB%84%E5%90%88%E6%88%90%E3%80%91%E5%93%88%E4%BD%9B%E5%8C%BB%E5%AD%A6%E9%99%A2%E5%9C%A8%E5%91%A8%E4%BA%8C%E4%B8%BE%E8%A1%8C%E4%BA%86%E4%B8%80%E5%9C%BA%E7%A7%98%E5%AF%86%E4%BC%9A%E8%AE%AE%EF%BC%8C%E8%BF%99%E5%9C%BA%E4%BC%9A%E8%AE%AE%E6%B2%A1%E6%9C%89%E9%82%80%E8%AF%B7%E4%BB%BB%E4%BD%95%E5%AA%92%E4%BD%93%E5%87%BA%E5%B8%AD%EF%BC%8C%E4%B8%94%E4%B8%8E%E4%BC%9A%E8%80%85%E8%A2%AB%E8%A6%81%E6%B1%82%E5%AF%B9%E6%AD%A4%E4%BA%8B%E5%AE%88%E5%8F%A3%E5%A6%82%E7%93%B6%E3%80%82%40%E7%85%8E%E8%9B%8B%E7%BD%91&pic=http%3A%2F%2Fjandan.net%2Fshare%2F2016%2F05%2Fp-78861.gif&style=number&language=zh_cn&button=pubilish" class="share-link share-link-weibo" target="_blank">分享到微博</a>
     <a href="javascript:;" class="share-link share-link-weixin"><img src="http://jandan.net/share/2016/05/qr-78861.png">分享到微信</a>
     </div>
     <script>
     $('.share-link-weixin').click(function(){
     $(this).find('img').toggle();
     });
     </script>

     */

    private PostBean post;
    private String previous_url;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public String getPrevious_url() {
        return previous_url;
    }

    public void setPrevious_url(String previous_url) {
        this.previous_url = previous_url;
    }

//    public static class PostBean extends RealmObject{
//        @PrimaryKey
//        private int id;
//        private String content;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//    }
}
