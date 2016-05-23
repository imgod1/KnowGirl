package com.kk.imgod.knowgirl.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.utils.AppUtils;
import com.kk.imgod.knowgirl.utils.BitmapUtil;
import com.kk.imgod.knowgirl.utils.DateUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;
import com.kk.imgod.knowgirl.utils.Ts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

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
public class PictureDetailFragment extends BaseFragment implements View.OnLongClickListener {
    private ImageView img_picture;


    public static final String IMGURL = "imgurl";
    private String imgUrl;

    private Bitmap bitmap;

    public static PictureDetailFragment newInstance(String imgurl) {
        PictureDetailFragment pictureDetailFragment = new PictureDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMGURL, imgurl);
        pictureDetailFragment.setArguments(bundle);
        return pictureDetailFragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_picture_detail;
    }

    @Override
    public void initView() {
        imgUrl = getArguments().getString(IMGURL);
        img_picture = (ImageView) parentView.findViewById(R.id.img_picture);

//        ImageLoader.loadWithHolder(getActivity(), imgUrl, R.mipmap.icon_app, img_picture);
        new LoadPictureTask().execute();
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        img_picture.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] items = {"分享到", "保存"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        new ShareIntentTask().execute(bitmap);
                        break;
                    case 1:
                        new SaveImageTask().execute(bitmap);
                        break;
                }
            }
        });
        dialog = builder.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        return true;
    }

    private class LoadPictureTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... voids) {

            try {
                bitmap = Glide.with(getActivity()).load(imgUrl)
                        .asBitmap()
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap picture) {
            if (isCancelled()) {
                return;
            }
            img_picture.setImageBitmap(picture);
        }
    }


    private class ShareIntentTask extends AsyncTask<Bitmap, Void, Uri> {

        @Override
        protected Uri doInBackground(Bitmap... params) {
            if (isCancelled()) {
                return null;
            }
            return BitmapUtil.bitmapToUri(getActivity(), params[0]);
        }

        @Override
        protected void onPostExecute(Uri result) {
            ShareUtils.shareImage(getActivity(), result);
        }
    }

    private class SaveImageTask extends AsyncTask<Bitmap, Void, File> {

        @Override
        protected File doInBackground(Bitmap... params) {
            if (isCancelled()) {
                return null;
            }
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getString(R.string.app_name) + DateUtils.parseStandardInfoDate(new Date()) + ".png");
            try {
                FileOutputStream stream = new FileOutputStream(file);
                params[0].compress(Bitmap.CompressFormat.PNG, 100, stream);
                stream.flush();
                stream.close();
                MediaScannerConnection.scanFile(getActivity(), new String[]{file.getPath()}, null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;
        }

        @Override
        protected void onPostExecute(File file) {
            if (file != null && file.exists()) {

                SnackBarUtils.showShort(parentView, getString(R.string.save_img_success)
                        + file.getAbsolutePath());
            } else {
                SnackBarUtils.showShort(parentView, R.string.save_img_failed);
            }
        }
    }

}
