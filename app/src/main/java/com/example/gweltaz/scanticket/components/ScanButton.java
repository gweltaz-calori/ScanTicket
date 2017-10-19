package com.example.gweltaz.scanticket.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.gweltaz.scanticket.stylekit.ScanButtonStyleKit;


/**
 * Created by gweltaz on 16/10/2017.
 */

public class ScanButton extends View {

    private int arcRotation = 0;
    private float centerScale = 0.2f;
    private int circleFilledPath = 103;

    private ScanStep currentStep = ScanStep.PROCESSING;
    private ValueAnimator moveBackAnimation;
    private ValueAnimator moveMaxAnimation;
    private AnimatorSet circleAnimatorSet = new AnimatorSet();
    private AnimatorSet arcAnimatorSet = new AnimatorSet();

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
        ScanButtonStyleKit.drawButton(canvas,new RectF(0,0,getWidth(),getHeight()), ScanButtonStyleKit.ResizingBehavior.AspectFit,arcRotation,centerScale, currentStep.getColor(),circleFilledPath);

    }

    private void createAnimation() {

        createArcAnimation();
        createCircleAnimation();

    }

    private void createArcAnimation() {
        ValueAnimator arcAnimation = ValueAnimator.ofInt(0, -360);
        arcAnimation.setDuration(currentStep.getSpeed());
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

        moveMaxAnimation = ValueAnimator.ofFloat(1.2f, 2.5f);
        moveMaxAnimation.setDuration(600);
        moveMaxAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                centerScale = value.floatValue();
                invalidate();
            }
        });


        moveBackAnimation = ValueAnimator.ofFloat(2.5f,0.2f);
        moveBackAnimation.setDuration(600);
        moveBackAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                centerScale = value.floatValue();
                invalidate();
            }
        });


        circleAnimatorSet.playSequentially(moveBeginningAnimation,moveMiddleAnimation,moveMaxAnimation,moveBackAnimation);

        circleAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (currentStep != ScanStep.READY) {
                    circleAnimatorSet.start();
                }
            }
        });

        circleAnimatorSet.setDuration(currentStep.getSpeed());
        circleAnimatorSet.start();



    }

    //for testing purposes
    public void changeStep() {

        if(currentStep == ScanStep.ERROR) {
            currentStep = ScanStep.READY;

            circleFilledPath = 17;
            moveBackAnimation.setFloatValues(2.5f,2.5f);
        }
        else if(currentStep == ScanStep.PROCESSING) {
            currentStep = ScanStep.ERROR;
            circleFilledPath = 103;
            moveBackAnimation.setFloatValues(2.5f,0.2f);
            circleAnimatorSet.resume();
        }
        else if(currentStep == ScanStep.READY) {
            currentStep = ScanStep.PROCESSING;
            circleFilledPath = 103;
            moveBackAnimation.setFloatValues(2.5f,0.2f);
            circleAnimatorSet.resume();
        }

        circleAnimatorSet.setDuration(currentStep.getSpeed());
        arcAnimatorSet.setDuration(currentStep.getSpeed());



    }

}
enum ScanStep {

    READY(Color.argb(255, 139, 195, 74),1000),
    ERROR(Color.argb(100, 239, 83, 80),800),
    PROCESSING(Color.argb(100, 255, 193, 7),400);

    private int color;
    private int speed;

    public int getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    ScanStep(int color, int speed) {
        this.color = color;
        this.speed = speed;
    }
}
