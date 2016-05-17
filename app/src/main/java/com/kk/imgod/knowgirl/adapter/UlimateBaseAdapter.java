package com.kk.imgod.knowgirl.adapter;

import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.List;

public abstract class UlimateBaseAdapter<D, URVH extends UltimateRecyclerviewViewHolder> extends easyRegularAdapter<D, URVH> {

  public  UlimateBaseAdapter(List<D> list){
      super(list);
  }

    @Override
    protected void withBindHolder(URVH holder, D data, int position) {
        initClickListener(holder, data, position);
    }

    protected void initClickListener(URVH holder, D data, final int position) {
        if (null != onItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }

        if (null != onItemLongClickListener) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }

    }

    //------------------设置长按和短按监听
    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //////////////////long click------------------
    public interface OnItemLongClickListener {
        public void onItemLongClick(View v, int position);
    }

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

}
