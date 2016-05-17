package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.Lg;

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
public class PictureDetailFragment extends BaseLazyFragment {
    private ImageView img_picture;
    private PhotoViewAttacher photoViewAttacher;


    public static final String IMGURL = "imgurl";
    private String imgUrl;

    public static PictureDetailFragment newInstance(String imgurl) {
        PictureDetailFragment pictureDetailFragment = new PictureDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMGURL, imgurl);
        pictureDetailFragment.setArguments(bundle);
        return pictureDetailFragment;
    }

    ;

    @Override
    protected void initData() {
        initView();
    }

    private void initView() {
        imgUrl = getArguments().getString(IMGURL);
        img_picture = (ImageView) parentView.findViewById(R.id.img_picture);
        photoViewAttacher = new PhotoViewAttacher(img_picture, true);
        ImageLoader.load(getActivity(), imgUrl, img_picture);
        Lg.e("PictureDetailFragment","imgUrl:"+imgUrl);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_picture_detail;
    }
}
