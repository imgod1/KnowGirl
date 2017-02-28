package com.kk.imgod.knowgirl.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.kk.imgod.knowgirl.R;
import com.kk.imgod.knowgirl.app.MyApp;
import com.kk.imgod.knowgirl.model.GifBean;
import com.kk.imgod.knowgirl.utils.BitmapUtil;
import com.kk.imgod.knowgirl.utils.DateUtils;
import com.kk.imgod.knowgirl.utils.SPUtils;
import com.kk.imgod.knowgirl.utils.ShareUtils;
import com.kk.imgod.knowgirl.utils.SnackBarUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;

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
public class GifDetailFragment extends BaseLazyFragment implements View.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.webview_main)
    WebView webview_main;
    public static final String IMAGE_HINT_FIRST = "gif_hint_first";


    public static final String GIFBEAN = "gifbean";
    private GifBean gifBean;

    private Bitmap bitmap;

    public static GifDetailFragment newInstance(GifBean gifBean) {
        GifDetailFragment pictureDetailFragment = new GifDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GIFBEAN, gifBean);
        pictureDetailFragment.setArguments(bundle);
        return pictureDetailFragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_gif_detail;
    }

    public void initView() {
        gifBean = (GifBean) getArguments().getSerializable(GIFBEAN);
    }

    public void initValue() {
        webview_main.loadUrl(gifBean.getGif_url());
    }

    public void initEvent() {
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
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] items = {getString(R.string.girl_share), getString(R.string.girl_save)};
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
                    default:
                        break;
                }
            }
        });
        dialog = builder.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
//                img_picture.setImageBitmap(bitmap);
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {
        if ((Boolean) SPUtils.get(MyApp.getAppContext(), IMAGE_HINT_FIRST, Boolean.TRUE)) {
            showHint();
            SPUtils.put(getActivity(), IMAGE_HINT_FIRST, false);
        }
    }

    @Override
    protected void initData() {
        initView();
        initEvent();
        initValue();
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
