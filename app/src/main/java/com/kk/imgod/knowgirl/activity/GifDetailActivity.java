package com.kk.imgod.knowgirl.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.adapter.FragmentViewPagerAdapter;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.fragment.GifDetailFragment;
import com.kk.imgod.knowgirl.model.GifBean;
import com.kk.imgod.knowgirl.utils.JsoupUtils;
import com.kk.imgod.knowgirl.utils.Lg;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import ooo.oxo.library.widget.PullBackLayout;

/**
 * 项目名称：KnowGirl
 * 类描述：Gif图片详情界面
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class GifDetailActivity extends BaseActivity implements PullBackLayout.Callback {
    /**
     * 静态的变量来存储详情界面需要的数据
     */
    @BindView(R.id.flayout_picture_main)
    PullBackLayout flayout_picture_main;
    @BindView(R.id.vp_pic_detail)
    ViewPager vp_pic_detail;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.txt_content)
    TextView txt_content;
    private static final String GIF_GROUP_URL = "gif_group_url";

    private String gif_group_url;
    private List<GifBean> gifList = new ArrayList<>();

    public static void actionStart(Activity activity, String gif_group_url) {
        Intent intent = new Intent(activity, GifDetailActivity.class);
        intent.putExtra(GIF_GROUP_URL, gif_group_url);
        activity.startActivity(intent);
    }


    @Override
    public int getLayoutResID() {
        setTheme(R.style.ViewerTheme_TransNav);
        return R.layout.activity_gif_detail;
    }


    @Override
    public void initView() {
        flayout_picture_main.setBackgroundColor(Color.BLACK);
        gif_group_url = getIntent().getStringExtra(GIF_GROUP_URL);
        flayout_picture_main.setCallback(this);
        vp_pic_detail.setOffscreenPageLimit(2);
    }

    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void initValue() {
        FragmentViewPagerAdapter fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), null, fragmentList);
        vp_pic_detail.setAdapter(fragmentViewPagerAdapter);
        getPicture();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragmentList.clear();
        for (int i = 0; i < gifList.size(); i++) {
            GifBean imageBean = gifList.get(i);
            Fragment fragment = GifDetailFragment.newInstance(imageBean);
            fragmentList.add(fragment);
        }
    }

    @Override
    public void initEvent() {
        vp_pic_detail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                txt_title.setText((position + 1) + "/" + gifList.size());
                txt_content.setText(gifList.get(position).getGif_content());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onPullStart() {

    }

    @Override
    public void onPull(float v) {
        flayout_picture_main.setBackgroundColor(Color.argb(0xff - (int) Math.floor(0xff * v), 0x00, 0x00, 0x00));
    }

    @Override
    public void onPullCancel() {

    }

    @Override
    public void onPullComplete() {
        //activity style明明禁用了动画效果.但是在flyme os上依然有动画
        //那么只好.如果是滑动退出那就设置动画效果为不存在的0,如果是点击了back 键,那就跟随系统吧
        finish();
        overridePendingTransition(0, 0);
    }

    private RequestCall requestCall;

    public void getPicture() {
        requestCall = OkHttpUtils.get().url(gif_group_url).build();
        requestCall.execute(new MyStringCallBack(this, flayout_picture_main) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                Log.e("pictureFragment", "onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (!TextUtils.isEmpty(response)) {
                    List<GifBean> gifBeanList = JsoupUtils.getGifBeanListFromHtml(response);
//                    DBUtils.saveList(MainActivity.realm, tempImageList);
                    gifList.clear();
                    gifList.addAll(gifBeanList);
                    String title = getString(R.string.start_tag) + gifList.size();
                    txt_title.setText(title);
                    txt_content.setText(gifList.get(0).getGif_content());
                    initFragment();
                    vp_pic_detail.getAdapter().notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (requestCall != null) {
            requestCall.cancel();
        }
    }
}
