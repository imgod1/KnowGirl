package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 项目名称：other_demo
 * 包名称：com.example.gaokang.other_demo
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016/4/26 16:55
 * 修改人：gaokang
 * 修改时间：2016/4/26 16:55
 * 修改备注：
 */
public class PictureDetailFragment extends Fragment {
    private View parentView;
    private ImageView img_picture;
    private PhotoViewAttacher photoViewAttacher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_picture_detail, container, false);
        initView();
        return parentView;
    }

    private void initView() {
        img_picture = (ImageView) parentView.findViewById(R.id.img_picture);
        photoViewAttacher = new PhotoViewAttacher(img_picture, true);
    }
}
