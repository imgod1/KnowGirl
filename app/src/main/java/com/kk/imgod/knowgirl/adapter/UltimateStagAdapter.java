package com.kk.imgod.knowgirl.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.app.API;
import com.kk.imgod.knowgirl.model.ImageBean;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.ScreenUtils;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.List;

import io.realm.Sort;

/**
 * Created by imgod on 2016/4/25.
 */
public class UltimateStagAdapter extends UlimateBaseAdapter<ImageBean, UltimateStagAdapter.MyViewHolder> {
    private Activity activity;
    int use_width;
    int use_height = 0;
    int imgClassId;

    public UltimateStagAdapter(Activity activity, List<ImageBean> list, int imgClassId) {
        super(list);
        this.activity = activity;
        this.imgClassId = imgClassId;
        use_width = (ScreenUtils.getWindowsWidth(activity) - 20) / 2;
        List<ImageBean> tempImageList = MainActivity.realm.where(ImageBean.class).equalTo("galleryclass", imgClassId).findAllSorted("id", Sort.DESCENDING);
        list.addAll(tempImageList);
        setHasStableIds(true);
    }

    @Override
    protected int getNormalLayoutResId() {
        return R.layout.item_stag;
    }

    @Override
    protected MyViewHolder newViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    protected void withBindHolder(final MyViewHolder holder, ImageBean data, int position) {
        super.withBindHolder(holder, data, position);
        final String img_url = API.PICTURE_BASE_URL + data.getImg();
//        Log.e("adapter", "img url:" + img_url + " data.getImg_height()" + data.getImg_height() + "data.getImg_width()" + data.getImg_width());
        use_height = use_width * data.getImg_height() / data.getImg_width();
        ViewGroup.LayoutParams layoutParams = holder.img_stag.getLayoutParams();
        layoutParams.height = use_height;
        layoutParams.width = use_width;
        holder.img_stag.setLayoutParams(layoutParams);
        ImageLoader.load(activity, img_url, holder.img_stag);
    }

    @Override
    public long getItemId(int position) {
        return source.get(position).hashCode();
    }

    public static class MyViewHolder extends UltimateRecyclerviewViewHolder {
        public ImageView img_stag;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_stag = (ImageView) itemView.findViewById(R.id.img_stag);
        }
    }

    @Override
    public int getItemCount() {
        return source == null ? 0 : source.size();
    }

    @Override
    public MyViewHolder newFooterHolder(View view) {
        return new MyViewHolder(view);
    }

}
