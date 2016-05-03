package com.kk.imgod.knowgirl.adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.utils.ScreenUtils;

import java.util.List;

/**
 * Created by imgod on 2016/4/24.
 */
public class StagAdapter extends RecyclerView.Adapter<StagAdapter.MyViewHolder> {
    private Activity context;
    private List<Integer> image_list;
    BitmapFactory.Options options;
    int use_width;
    int use_height = 0;

    public StagAdapter(Activity context, List<Integer> image_list) {
        this.context = context;
        this.image_list = image_list;
        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        use_width = ScreenUtils.getWindowsWidth(context) / 2 - 20;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stag, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), image_list.get(position % image_list.size()), options);
        use_height = use_width * options.outHeight / options.outWidth;
        Log.e("onBindViewHolder", "position % image_list.size():" + position % image_list.size());
        Log.e("onBindViewHolder", "width:" + use_width);
        Log.e("onBindViewHolder", "height:" + use_height);
        holder.img_stag.setLayoutParams(new ViewGroup.LayoutParams(use_width, use_height));
        holder.img_stag.setImageResource(image_list.get(position % image_list.size()));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_stag;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_stag = (ImageView) itemView.findViewById(R.id.img_stag);
        }
    }
}
