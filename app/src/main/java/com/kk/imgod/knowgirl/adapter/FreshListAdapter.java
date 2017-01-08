package com.kk.imgod.knowgirl.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.activity.MainActivity;
import com.kk.imgod.knowgirl.model.FreshBean;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import java.util.List;

import io.realm.Sort;

public class FreshListAdapter extends UlimateBaseAdapter<FreshBean, FreshListAdapter.MyViewHolder> {
    private Activity activity;

    public FreshListAdapter(Activity activity, List<FreshBean> list) {
        super(list);
        this.activity = activity;
        setHasStableIds(true);
        List<FreshBean> freshBeans = MainActivity.realm.where(FreshBean.class).findAllSorted("date", Sort.DESCENDING);
        list.addAll(freshBeans);
    }


    @Override
    protected int getNormalLayoutResId() {
        return R.layout.item_fresh;
    }

    @Override
    protected MyViewHolder newViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    protected void withBindHolder(final MyViewHolder holder, final FreshBean data, int position) {
        super.withBindHolder(holder, data, position);
        ImageLoader.load(activity, data.getCustom_fields().getThumb_c().get(0).getVal(), holder.img_fresh_news, R.drawable.icon_app);
        holder.txt_fresh_title.setText(data.getTitle());
    }


    @Override
    public long getItemId(int position) {
        return source.get(position).hashCode();
    }

//    @Override
//    public void onChange(Object element) {
//        notifyDataSetChanged();
//    }


    public static class MyViewHolder extends UltimateRecyclerviewViewHolder {
        public ImageView img_fresh_news;
        public TextView txt_fresh_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_fresh_news = (ImageView) itemView.findViewById(R.id.img_fresh_news);
            txt_fresh_title = (TextView) itemView.findViewById(R.id.txt_fresh_title);
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
