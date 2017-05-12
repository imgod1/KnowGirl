package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.ZhihuDetail;
import com.kk.imgod.knowgirl.model.ZhihuStory;
import com.kk.imgod.knowgirl.utils.DBUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.BindView;
import okhttp3.Call;
/**
 * 项目名称：KnowGirl
 * 类描述：知乎日报详情
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class ZhiHuDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbar_layout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_detail)
    ImageView img_detail;
    @BindView(R.id.flayout_content)
    FrameLayout flayout_content;
    @BindView(R.id.contentLoadingProgressBar)
    ContentLoadingProgressBar contentLoadingProgressBar;

    WebView webView;
    public static final String ZHIHUSTOREYID = "zhihuStoreyId";
    private int zhihuStoryId;
    private ZhihuStory zhihuStory;

    public static void actionStart(Activity activity, int zhihuStoryId) {
        Intent intent = new Intent(activity, ZhiHuDetailActivity.class);
        intent.putExtra(ZHIHUSTOREYID, zhihuStoryId);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_zhi_hu_detail;
    }

    @Override
    public void initView() {
        zhihuStoryId = getIntent().getIntExtra(ZHIHUSTOREYID, 0);
        zhihuStory = MainActivity.realm.where(ZhihuStory.class).equalTo("id", zhihuStoryId).findFirst();
        setSupportActionBar(toolbar);
    }

    @Override
    public void initValue() {
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());//设置了这个,点击了网页中的连接.跳转依然在本webview中进行
        flayout_content.addView(webView);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (100 == newProgress) {
                    contentLoadingProgressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        zhihuDetail = MainActivity.realm.where(ZhihuDetail.class).equalTo("id", zhihuStoryId).findFirst();
        if (zhihuDetail != null) {
            showDetail(zhihuDetail.getBody());
            ImageLoader.load(ZhiHuDetailActivity.this, zhihuDetail.getImage(), img_detail);
        } else {
            getData("" + zhihuStory.getId());
        }

        toolbar_layout.setTitle(zhihuStory.getTitle());
        toolbar.setTitle(zhihuStory.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initEvent() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                ShareUtils.shareText(ZhiHuDetailActivity.this, zhihuDetail.getShare_url());
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zhi_hu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_share:
                ShareUtils.shareText(ZhiHuDetailActivity.this, getText(R.string.zhihu_detail_share_title) + zhihuDetail.getShare_url());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private RequestCall requestCall;

    private ZhihuDetail zhihuDetail;

    private void getData(String id) {
        requestCall = OkHttpUtils.get().url(API.ZHIHU_BASE_URL + id).build();
        requestCall.execute(new MyStringCallBack(ZhiHuDetailActivity.this, flayout_content) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                contentLoadingProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (!TextUtils.isEmpty(response)) {
                    zhihuDetail = GsonUtils.getGson().fromJson(response, ZhihuDetail.class);
                    if (zhihuDetail != null) {
                        ImageLoader.load(ZhiHuDetailActivity.this, zhihuDetail.getImage(), img_detail);
                        showDetail(zhihuDetail.getBody());
                        DBUtils.copyOrUpdateRealm(MainActivity.realm, zhihuDetail);
                    }
                }
            }
        });
    }

    public void showDetail(String detailNews) {
        //add css style to webView
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + detailNews + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    @Override
    protected void onDestroy() {
        if (requestCall != null) {
            requestCall.cancel();
        }
        webView.removeAllViews();
        webView.clearHistory();
        webView = null;
        super.onDestroy();
    }
}
