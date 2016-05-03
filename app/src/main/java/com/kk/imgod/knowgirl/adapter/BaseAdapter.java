package com.kk.imgod.knowgirl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kk.imgod.knowgirl.interf.OnItemClickListener;
import com.kk.imgod.knowgirl.interf.OnItemLongClickListener;

/**
 * Created by imgod on 2016/4/24.
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    public OnItemClickListener onItemClickListener;
    public OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void onBindViewHolder(T a, final int position) {
        if (null != onItemClickListener) {
            a.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }

        if (null != onItemLongClickListener) {
            a.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

}
