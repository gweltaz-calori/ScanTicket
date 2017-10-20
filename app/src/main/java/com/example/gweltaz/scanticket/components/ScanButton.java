package com.example.gweltaz.scanticket.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.gweltaz.scanticket.interfaces.OnStepChangedListener;
import com.example.gweltaz.scanticket.stylekit.ScanButtonStyleKit;


public class ScanButton extends View {

    private int arcRotation = 0;
    private float centerScale = 0.2f;
    private int circleFilledPath = 103;

    private ScanStep currentStep = ScanStep.PROCESSING;
    private ValueAnimator moveBackAnimation;
    private ValueAnimator moveMaxAnimation;
    private AnimatorSet circleAnimatorSet = new AnimatorSet();
    private AnimatorSet arcAnimatorSet = new AnimatorSet();

    private OnStepChangedListener stepChangedListener;

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

    public void setOnStepChangedListener(OnStepChangedListener listener) {
        stepChangedListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ScanButtonStyleKit.drawButton(canvas,new RectF(0,0,getWidth(),getHeight()), ScanButtonStyleKit.ResizingBehavior.AspectFit,arcRotation,centerScale, currentStep.getColor(),circleFilledPath);

    }

    private void createAnimation() {

        //Flou sinon à cause de l'hardware
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        createArcAnimation();
        createCircleAnimation();

    }

    private void createArcAnimation() {
        ValueAnimator arcAnimation = ValueAnimator.ofInt(0, -360);
        arcAnimation.setDuration(currentStep.getSpeed());
        arcAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                arcRotation = value;
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
                centerScale = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });


        ValueAnimator moveMiddleAnimation = ValueAnimator.ofFloat(0.8f, 1.2f);
        moveMiddleAnimation.setDuration(600);

        moveMiddleAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                centerScale = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });

        moveMaxAnimation = ValueAnimator.ofFloat(1.2f, 2.5f);
        moveMaxAnimation.setDuration(600);
        moveMaxAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                centerScale = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });


        moveBackAnimation = ValueAnimator.ofFloat(2.5f,0.2f);
        moveBackAnimation.setDuration(600);
        moveBackAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                centerScale = (Float) animation.getAnimatedValue();
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
                else {
                    stepChangedListener.onSuccess();
                }
            }
        });

        circleAnimatorSet.setDuration(currentStep.getSpeed());
        circleAnimatorSet.start();



    }

    //for testing purposes , remove this with scan
    public void changeStep() {

        if(currentStep == ScanStep.ERROR) {
            currentStep = ScanStep.READY;
            circleFilledPath = 17;
            moveBackAnimation.setFloatValues(2.5f,2.5f);
        }
        else if(currentStep == ScanStep.PROCESSING) {
            currentStep = ScanStep.ERROR;
            stepChangedListener.onError();

        }
        else if(currentStep == ScanStep.READY) {
            currentStep = ScanStep.PROCESSING;
            circleFilledPath = 103;
            moveBackAnimation.setFloatValues(2.5f,0.2f);

            circleAnimatorSet.cancel();
            circleAnimatorSet.start();
        }

        circleAnimatorSet.setDuration(currentStep.getSpeed());
        arcAnimatorSet.setDuration(currentStep.getSpeed());

    }

    public void show() {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(this,"translationY",200,0);
        translateY.setInterpolator(new AnticipateInterpolator());
        translateY.setDuration(1000);
        translateY.start();
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
