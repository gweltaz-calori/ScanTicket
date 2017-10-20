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
import android.view.animation.LinearInterpolator;

import com.example.gweltaz.scanticket.interfaces.OnStepChangedListener;
import com.example.gweltaz.scanticket.enums.ScanStep;
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

    private boolean mCanceled = false;

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
                arcRotation = (Integer) animation.getAnimatedValue();
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

                if(!mCanceled) {

                    if (currentStep != ScanStep.READY) {
                        circleAnimatorSet.start();
                    }
                    else {
                        stepChangedListener.onSuccess();
                    }

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCanceled = true;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mCanceled = false;
            }

        });

        circleAnimatorSet.setDuration(currentStep.getSpeed());
        circleAnimatorSet.start();



    }

    //for testing purposes , remove this with real scan
    public void changeStep() {

        if(currentStep == ScanStep.ERROR) { //next step is ready
            currentStep = ScanStep.READY;
            circleFilledPath = 17;
            moveBackAnimation.setFloatValues(2.5f,2.5f);
        }
        else if(currentStep == ScanStep.PROCESSING) { //set next step error
            currentStep = ScanStep.ERROR;
            stepChangedListener.onError();

        }
        else if(currentStep == ScanStep.READY) { //set next step to processing
            currentStep = ScanStep.PROCESSING;
            circleFilledPath = 103;
            moveBackAnimation.setFloatValues(2.5f,0.2f);

            circleAnimatorSet.cancel();
            circleAnimatorSet.start();
        }


        circleAnimatorSet.setDuration(currentStep.getSpeed());
        arcAnimatorSet.setDuration(currentStep.getSpeed());

    }

    public void changeMode(boolean isAuto) {
        //todo change mode
        if(isAuto) {

        }
    }






    // appear animation
    public void show() {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(this,"translationY",200,0);
        translateY.setInterpolator(new AnticipateInterpolator());
        translateY.setDuration(1000);
        translateY.start();
    }

}

