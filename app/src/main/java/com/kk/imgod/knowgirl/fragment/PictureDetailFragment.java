package com.kk.imgod.knowgirl.fragment;

import android.app.AlertDialog;
import android.app.WallpaperManager;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.MyApp;
import com.kk.imgod.knowgirl.utils.AppUtils;
import com.kk.imgod.knowgirl.utils.BitmapUtil;
import com.kk.imgod.knowgirl.utils.BlurBuilder;
import com.kk.imgod.knowgirl.utils.DateUtils;
import com.kk.imgod.knowgirl.utils.ImageLoader;
import com.kk.imgod.knowgirl.utils.Lg;
import com.kk.imgod.knowgirl.utils.SPUtils;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;
import com.kk.imgod.knowgirl.utils.Ts;
import com.kk.imgod.knowgirl.utils.WallpaperUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * 项目名称：
 * 包名称：
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016/4/26 16:55
 * 修改人：gaokang
 * 修改时间：2016/4/26 16:55
 * 修改备注：
 */
public class PictureDetailFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {

    public static final String IMAGE_HINT_FIRST = "Image_hint_first";

    private ImageView img_picture;
    private ProgressBar progress_bar;

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
        progress_bar = (ProgressBar) parentView.findViewById(R.id.progress_bar);
//        ImageLoader.loadWithHolder(getActivity(), imgUrl, R.mipmap.icon_app, img_picture);
        new LoadPictureTask().execute();
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        img_picture.setOnLongClickListener(this);
        img_picture.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap = null;
        }
    }

    private void showHint() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.hint).
                setMessage(R.string.view_img_hint).
                setPositiveButton(R.string.i_see, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).
                create().show();
    }


    @Override
    public boolean onLongClick(View v) {
        if (null == bitmap) {
            SnackBarUtils.showShort(parentView, "图片还在加载中...");
            return true;
        }
        new BlurTask().execute(bitmap);

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] items = {getString(R.string.girl_share), getString(R.string.girl_save), getString(R.string.girl_set_wallpaper)};
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
                    case 2:
                        setWallpaper(bitmap);
                        break;
                }
            }
        });
        dialog = builder.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                img_picture.setImageBitmap(bitmap);
            }
        });
        return true;
    }

    private void setWallpaper(final Bitmap bitmap) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                return WallpaperUtils.setWallpaperByBitmap(getActivity(), bitmap);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    Ts.showShort(getActivity(), "恭喜陛下,此女子已经成功侍寝");
                } else {
                    Ts.showShort(getActivity(), "禀告陛下,此女子誓死不从");
                }
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        if ((Boolean) SPUtils.get(MyApp.getAppContext(), IMAGE_HINT_FIRST, Boolean.TRUE)) {
            showHint();
            SPUtils.put(getActivity(), IMAGE_HINT_FIRST, false);
        }
    }


    private class LoadPictureTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... voids) {
            if (getActivity() != null) {
                try {
                    bitmap = Glide.with(getActivity()).load(imgUrl)
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap picture) {
            progress_bar.setVisibility(View.GONE);
            if (isCancelled()) {
                return;
            }
            if (null != picture) {
                img_picture.setImageBitmap(picture);
            }

        }
    }

    private class BlurTask extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Bitmap... bitmaps) {
            if (isCancelled()) {
                return null;
            }
            //change the 'reuseBitmap' to true to blur the image persistently
            return BlurBuilder.blur(bitmaps[0]);
        }

        @Override
        protected void onPostExecute(Bitmap blurBitmap) {
            if (blurBitmap != null) {
                img_picture.setImageBitmap(blurBitmap);
            }

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
