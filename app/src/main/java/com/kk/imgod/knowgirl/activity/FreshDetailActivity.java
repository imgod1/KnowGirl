package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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
import com.kk.imgod.knowgirl.model.FreshBean;
import com.kk.imgod.knowgirl.model.FreshDetail;
import com.kk.imgod.knowgirl.model.PostBean;
import com.kk.imgod.knowgirl.model.ZhihuDetail;
import com.kk.imgod.knowgirl.model.ZhihuStory;
import com.kk.imgod.knowgirl.utils.DBUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 项目名称：KnowGirl
 * 类描述：煎蛋详情界面
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class FreshDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.flayout_content)
    FrameLayout flayout_content;
    @BindView(R.id.contentLoadingProgressBar)
    ContentLoadingProgressBar contentLoadingProgressBar;

    WebView webView;
    public static final String FRESHBEAN = "freshbean";
    private FreshBean freshBean;
    private int freshid;

    public static void actionStart(Activity activity, int id) {
        Intent intent = new Intent(activity, FreshDetailActivity.class);
        intent.putExtra(FRESHBEAN, id);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_fresh_detail;
    }

    @Override
    public void initView() {
        freshid = getIntent().getIntExtra(FRESHBEAN, 0);
        freshBean = MainActivity.realm.where(FreshBean.class).equalTo("id", freshid).findFirst();
        toolbar.setTitle(freshBean.getTitle());
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

        PostBean postBean = MainActivity.realm.where(PostBean.class).equalTo("id", freshBean.getId()).findFirst();
        if (null != postBean) {
            showDetail(postBean.getContent());
        } else {
            getData("" + freshBean.getId());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

    private FreshDetail freshDetail;

    private void getData(String id) {
        requestCall = OkHttpUtils.get().url(API.JIANDAN_FRESH_NEWS_DETAIL + id).build();
        requestCall.execute(new MyStringCallBack(FreshDetailActivity.this, flayout_content) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                Log.e("pictureFragment", "onError:" + e.getMessage());
                contentLoadingProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (!TextUtils.isEmpty(response)) {
                    freshDetail = GsonUtils.getGson().fromJson(response, FreshDetail.class);
                    if (freshDetail != null) {
                        DBUtils.copyOrUpdateRealm(MainActivity.realm, freshDetail.getPost());
                        showDetail(freshDetail.getPost().getContent());
                    } else {
                        SnackBarUtils.showShort(flayout_content, "没有得到任何数据");
                    }

                }
            }
        });
    }

    public void showDetail(String detailNews) {
        webView.loadDataWithBaseURL("x-data://base", detailNews, "text/html", "UTF-8", null);
    }

    @Override
    protected void onDestroy() {
        if (null != requestCall) {
            requestCall.cancel();
        }
        webView.removeAllViews();
        webView.clearHistory();
        webView = null;
        super.onDestroy();
    }

    ShareActionProvider mShareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        mShareActionProvider.setShareIntent(
                ShareUtils.getShareTextIntent(freshBean.getUrl()));
        return super.onCreateOptionsMenu(menu);
    }
}
