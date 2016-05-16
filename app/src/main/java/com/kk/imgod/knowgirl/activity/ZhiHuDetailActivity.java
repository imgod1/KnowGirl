package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.model.ZhihuDetail;
import com.kk.imgod.knowgirl.model.ZhihuResponse;
import com.kk.imgod.knowgirl.model.ZhihuStory;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.BindView;
import okhttp3.Call;

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
    public static final String ZHIHUSTOREY = "zhihuStorey";
    private ZhihuStory zhihuStory;

    public static void actionStart(Activity activity, ZhihuStory zhihuStory) {
        Intent intent = new Intent(activity, ZhiHuDetailActivity.class);
        intent.putExtra(ZHIHUSTOREY, zhihuStory);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_zhi_hu_detail;
    }

    @Override
    public void initView() {
        zhihuStory = (ZhihuStory) getIntent().getSerializableExtra(ZHIHUSTOREY);
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
        getData("" + zhihuStory.getId());
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
                ShareUtils.shareText(ZhiHuDetailActivity.this,zhihuDetail.getShare_url());
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private RequestCall requestCall;

    private ZhihuDetail zhihuDetail;
    private void getData(String id) {
        requestCall = OkHttpUtils.get().url(API.ZHIHU_BASE_URL + id).build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e("pictureFragment", "onError:" + e.getMessage());
                SnackBarUtils.showShort(flayout_content, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e("getLastData", "response:" + response);
                if (!TextUtils.isEmpty(response)) {
                    zhihuDetail = GsonUtils.getGson().fromJson(response, ZhihuDetail.class);
                    ImageLoader.load(ZhiHuDetailActivity.this, zhihuDetail.getImage(), img_detail);
                    if (zhihuDetail != null) {
                        showDetail(zhihuDetail.getBody());
                    } else {
                        SnackBarUtils.showShort(flayout_content, "没有得到任何数据");
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
        requestCall.cancel();
        webView.removeAllViews();
        webView.clearHistory();
        webView = null;
        super.onDestroy();
    }
}
