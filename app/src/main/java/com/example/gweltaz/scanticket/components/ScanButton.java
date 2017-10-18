package com.example.gweltaz.scanticket.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.gweltaz.scanticket.stylekit.ScanButtonStyleKit;


/**
 * Created by gweltaz on 16/10/2017.
 */

public class ScanButton extends View {

    private int arcRotation = 0;
    private float centerScale = 0.2f;

    public ScanButton(Context context) {
        super(context);
        createAnimation();
    }

    public ScanButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createAnimation();
    }

    public ScanButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ScanButtonStyleKit.drawButton(canvas,new RectF(0,0,getWidth(),getHeight()), ScanButtonStyleKit.ResizingBehavior.AspectFit,arcRotation,centerScale);

    }

    private void createAnimation() {

        createArcAnimation();
        createCircleAnimation();

    }

    private void createArcAnimation() {
        ValueAnimator arcAnimation = ValueAnimator.ofInt(0, -360);
        arcAnimation.setDuration(1000);
        arcAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                arcRotation = value.intValue();
                invalidate();
            }
        });
        arcAnimation.setRepeatCount(ValueAnimator.INFINITE);
        arcAnimation.setRepeatMode(ValueAnimator.RESTART);
        arcAnimation.setInterpolator(new LinearInterpolator());
        arcAnimation.start();

    }

    private void createCircleAnimation() {

        ValueAnimator moveBeginningAnimation = ValueAnimator.ofFloat(0.2f, 0.8f);
        moveBeginningAnimation.setDuration(600);
        moveBeginningAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                centerScale = value.floatValue();
                invalidate();
            }
        });


        ValueAnimator moveMiddleAnimation = ValueAnimator.ofFloat(0.8f, 1.2f);
        moveMiddleAnimation.setDuration(600);

        moveMiddleAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                centerScale = value.floatValue();
                invalidate();
            }
        });

        ValueAnimator moveMaxAnimation = ValueAnimator.ofFloat(1.2f, 2.9f);
        moveMaxAnimation.setDuration(600);
        moveMaxAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                centerScale = value.floatValue();
                invalidate();
            }
        });


        ValueAnimator moveBackAnimation = ValueAnimator.ofFloat(2.9f,0.2f);
        moveBackAnimation.setDuration(600);
        moveBackAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                centerScale = value.floatValue();
                invalidate();
            }
        });


        final AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playSequentially(moveBeginningAnimation,moveMiddleAnimation,moveMaxAnimation,moveBackAnimation);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet.start();
            }
        });

        animatorSet.start();



    }

}
