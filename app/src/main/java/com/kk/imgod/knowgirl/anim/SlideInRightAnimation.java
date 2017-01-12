package com.kk.imgod.knowgirl.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class SlideInRightAnimation implements BaseAnimation {
    public SlideInRightAnimation() {
    }

    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationX", new float[]{(float) view.getRootView().getWidth(), 0.0F})};
    }
}