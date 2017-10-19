package com.example.gweltaz.scanticket.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.gweltaz.scanticket.interpolator.ReverseInterpolator;


public class AgkitMessage extends AppCompatTextView {

    private AnimatorSet bubbleAnimation;

    public AgkitMessage(Context context) {
        super(context);
        initView();
    }

    public AgkitMessage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AgkitMessage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),"Muli-Regular.ttf");
        setTypeface(font);
        createAnimation();
    }

    private void createAnimation() {

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this,"scaleY",0,1);
        scaleY.setDuration(300);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this,"scaleX",0,1);
        scaleX.setDuration(300);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(this,"alpha",0,1);
        alpha.setDuration(300);


        bubbleAnimation = new AnimatorSet();

        bubbleAnimation.playTogether(scaleY,scaleX,alpha);
        bubbleAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                AgkitMessage.this.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
    }

    public void animateIn() {
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        bubbleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        bubbleAnimation.start();
    }

    public void animateOut() {
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        bubbleAnimation.setInterpolator(new ReverseInterpolator());
        bubbleAnimation.start();
    }



}
