package com.kk.imgod.knowgirl.fragment;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.ChangeImageTransform;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.activity.ZhiHuDetailActivity;
import com.kk.imgod.knowgirl.anim.SlideInLeftAnimation;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.app.Constant;
import com.kk.imgod.knowgirl.customerclass.MyStringCallBack;
import com.kk.imgod.knowgirl.model.ZhihuResponse;
import com.kk.imgod.knowgirl.model.ZhihuStory;
import com.kk.imgod.knowgirl.utils.DBUtils;
import com.kk.imgod.knowgirl.utils.DateUtils;
import com.kk.imgod.knowgirl.utils.GsonUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Sort;
import okhttp3.Call;

/**
 * 项目名称：KnowGirl
 * 类描述：知乎日报
 * 创建人：imgod
 * 创建时间：2016/4/24 16:20
 * 修改人：imgod
 * 修改时间：2016/4/24 16:20
 * 修改备注：
 */
public class ZhihuFragment extends RecyclerViewFragment {
    private String willLoadDate;
    private List<ZhihuStory> zhihuStories;

    @Override
    protected void initData() {
        super.initData();
        willLoadDate = DateUtils.parseStandardDate(new Date());
        zhihuStories = new ArrayList<>();
        //先取数据库
        List<ZhihuStory> tempList = MainActivity.realm.where(ZhihuStory.class).findAllSorted("id", Sort.DESCENDING);
        zhihuStories.addAll(tempList);
        initAdapter();
        initEvent();
    }

    private void initEvent() {
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLastData();
            }
        });
    }

    private void initAdapter() {
        CommonAdapter<ZhihuStory> zhiHuAdapter = new CommonAdapter<ZhihuStory>(getActivity(), R.layout.item_news, zhihuStories) {
            @Override
            protected void convert(ViewHolder holder, ZhihuStory zhihuStory, int position) {
                ImageView img_news = holder.getView(R.id.img_news);
                TextView txt_title = holder.getView(R.id.txt_title);
                if (zhihuStory != null) {
                    if (zhihuStory.getImages() != null && zhihuStory.getImages().size() != 0) {
                        ImageLoader.load(getActivity(), zhihuStory.getImages().get(0).getVal(), img_news, R.drawable.icon_app);
                    }
                    txt_title.setText(zhihuStory.getTitle());
                }
                View view = holder.getConvertView();
                SlideInLeftAnimation slideInLeftAnimation = new SlideInLeftAnimation();
                Animator[] animators = slideInLeftAnimation.getAnimators(view);
                animators[0].start();
            }
        };

        zhiHuAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ImageView img_news = (ImageView) view.findViewById(R.id.img_news);
                startDetailActivity(img_news, position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        LoadMoreWrapper loadMoreWrapper = new LoadMoreWrapper(zhiHuAdapter);
        loadMoreWrapper.setLoadMoreView(R.layout.layout_loading_more);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getBeforeData();
            }
        });

        recyclerview.setAdapter(loadMoreWrapper);


    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    /**
     * 跳转到详情界面
     *
     * @param view     共享元素视图
     * @param position 下标
     */
    private void startDetailActivity(View view, int position) {
        Intent intent = new Intent(getActivity(), ZhiHuDetailActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition transition = new ChangeImageTransform();
            transition.setDuration(3000);
            getActivity().getWindow().setExitTransition(transition);
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), Pair.create((View) view, "big_img"));
            Bundle bundle = activityOptions.toBundle();
            intent.putExtra(ZhiHuDetailActivity.ZHIHUSTOREYID, zhihuStories.get(position).getId());
            startActivity(intent, bundle);
        } else {
            ZhiHuDetailActivity.actionStart(getActivity(), zhihuStories.get(position).getId());
        }
    }


    private RequestCall requestCall;

    public void getLastData() {
        requestCall = OkHttpUtils.get().url(API.ZHIHU_NEWS_LATEST).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl_main.setRefreshing(false);
                    }
                }, Constant.DELAYTIME);
            }

            @Override
            public void onResponse(String response) {
                srl_main.setRefreshing(false);
                if (!TextUtils.isEmpty(response)) {
                    ZhihuResponse zhihuResponse = GsonUtils.getGson().fromJson(response, ZhihuResponse.class);
                    if (zhihuResponse != null) {
                        DBUtils.copyOrUpdateRealm(MainActivity.realm, zhihuResponse);
                        willLoadDate = zhihuResponse.getDate();
                        zhihuStories.clear();
                        //数据保存
                        List<ZhihuStory> tempList = zhihuResponse.getStories();
                        DBUtils.saveList(MainActivity.realm, tempList);
                        //更新视图
                        zhihuStories.addAll(tempList);
                        recyclerview.getAdapter().notifyDataSetChanged();
                    }

                }
            }
        });
    }

    public void getBeforeData() {
        requestCall = OkHttpUtils.get().url(API.ZHIHU_NEWS_BEFORE + willLoadDate).build();
        requestCall.execute(new MyStringCallBack(getActivity(), ((MainActivity) getActivity()).getMainCoordinatorLayout()) {
            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                showOrHideRefresh(false);
            }

            @Override
            public void onResponse(String response) {
                showOrHideRefresh(false);
                if (!TextUtils.isEmpty(response)) {
                    ZhihuResponse zhihuResponse = GsonUtils.getGson().fromJson(response, ZhihuResponse.class);
                    if (zhihuResponse != null) {
                        List<ZhihuStory> tempList = zhihuResponse.getStories();
                        DBUtils.saveList(MainActivity.realm, tempList);
                        zhihuStories.addAll(tempList);
                        willLoadDate = zhihuResponse.getDate();
                        recyclerview.getAdapter().notifyItemRangeChanged(zhihuStories.size() - tempList.size(), tempList.size());
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != requestCall) {
            requestCall.cancel();
        }
    }
}
