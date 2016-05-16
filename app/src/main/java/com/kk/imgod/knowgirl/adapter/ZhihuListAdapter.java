package com.kk.imgod.knowgirl.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.model.ZhihuStory;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.List;

public class ZhihuListAdapter extends easyRegularAdapter<ZhihuStory, ZhihuListAdapter.MyViewHolder> {
    private Activity activity;
    public ZhihuListAdapter(Activity activity, List<ZhihuStory> list) {
        super(list);
        this.activity = activity;
        setHasStableIds(true);
    }

    @Override
    protected int getNormalLayoutResId() {
        return R.layout.item_news;
    }

    @Override
    protected MyViewHolder newViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    protected void withBindHolder(final MyViewHolder holder, ZhihuStory data, int position) {
        ImageLoader.load(activity,data.getImages().get(0),holder.img_news);
        holder.txt_title.setText(data.getTitle());
    }

    @Override
    public long getItemId(int position) {
        return source.get(position).hashCode();
    }

    public static class MyViewHolder extends UltimateRecyclerviewViewHolder {
        public ImageView img_news;
        public TextView txt_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            img_news = (ImageView) itemView.findViewById(R.id.img_news);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
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
