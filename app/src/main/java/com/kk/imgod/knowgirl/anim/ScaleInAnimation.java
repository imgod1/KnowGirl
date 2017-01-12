package com.kk.imgod.knowgirl.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * 缩放动画
 */
public class ScaleInAnimation implements BaseAnimation {
    private static final float DEFAULT_SCALE_FROM = 0.5F;
    private final float mFrom;

    public ScaleInAnimation() {
        this(0.5F);
    }

    public ScaleInAnimation(float from) {
        this.mFrom = from;
    }

    public Animator[] getAnimators(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", new float[]{this.mFrom, 1.0F});
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", new float[]{this.mFrom, 1.0F});
        return new ObjectAnimator[]{scaleX, scaleY};
    }
}