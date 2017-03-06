package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.app.MyApp;
import com.kk.imgod.knowgirl.model.GifBean;
import com.kk.imgod.knowgirl.utils.AssetsUtils;
import com.kk.imgod.knowgirl.utils.ShareUtils;

import butterknife.BindView;

/**
 * 项目名称：
 * 包名称：
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016/4/26 16:55
 * 修改人：gaokang
 * 修改时间：2016/4/26 16:55
 * 修改备注：
 */
public class GifDetailFragment extends BaseLazyFragment implements View.OnClickListener {
    public static String html_content;
    @BindView(R.id.rlayout_main)
    RelativeLayout rlayout_main;
    WebView webview_main;

    public static final String GIFBEAN = "gifbean";
    private GifBean gifBean;

    public static GifDetailFragment newInstance(GifBean gifBean) {
        GifDetailFragment pictureDetailFragment = new GifDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GIFBEAN, gifBean);
        pictureDetailFragment.setArguments(bundle);
        return pictureDetailFragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_gif_detail;
    }

    public void initView() {
        gifBean = (GifBean) getArguments().getSerializable(GIFBEAN);
        webview_main = new WebView(MyApp.getAppContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        webview_main.setLayoutParams(layoutParams);
        rlayout_main.addView(webview_main);
        webview_main.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview_main.getSettings().setLoadWithOverviewMode(true);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.floatingbtn, rlayout_main, false);
        FloatingActionButton flb_share = (FloatingActionButton) view.findViewById(R.id.flb_share);
        rlayout_main.addView(view);
        flb_share.setOnClickListener(this);
    }

    public void initValue() {
        if (TextUtils.isEmpty(html_content)) {
            html_content = AssetsUtils.getFromAssets(getContext(), "html/gif.html");
        }
        String real_html = html_content.replace("imgod_gif_url", gifBean.getGif_url());
        webview_main.loadDataWithBaseURL("about:blank", real_html, "text/html", "UTF-8", null);
    }

    public void initEvent() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initData() {
        initView();
        initEvent();
        initValue();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flb_share:
                String share_url = Constant.GIF_SHARE_URL.replace(Constant.GROUPID, "" + gifBean.getGroup_id());
                share_url = share_url.replace(Constant.GIFID, "" + gifBean.getReal_id());
                ShareUtils.shareText(getContext(), share_url);
                break;
            default:
                break;
        }
    }
}
