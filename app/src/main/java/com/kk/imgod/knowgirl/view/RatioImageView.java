package com.kk.imgod.knowgirl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 项目名称：KnowGirl
 * 包名称：com.kk.imgod.knowgirl.view
 * 类描述：
 * 创建人：gaokang
 * 创建时间：2016/4/28 10:54
 * 修改人：gaokang
 * 修改时间：2016/4/28 10:54
 * 修改备注：
 */
public class RatioImageView extends ImageView {

    private int originalWidth;
    private int originalHeight;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = (float) originalWidth / (float) originalHeight;

            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);

            // TODO: 现在只支持固定宽度
            if (width > 0) {
                height = (int) ((float) width / ratio);
            }

            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
