package com.example.gweltaz.scanticket.components;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import com.example.gweltaz.scanticket.stylekit.HelpButtonStyleKit;


public class HelpButton extends View{

    private int opacity = 30;

    public HelpButton(Context context) {
        super(context);
        initView();
    }

    public HelpButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HelpButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        HelpButtonStyleKit.drawHelpButton(canvas,new RectF(0,0,getWidth(),getHeight()), HelpButtonStyleKit.ResizingBehavior.AspectFit,opacity);
    }

    //only for changing opacity when clicking
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                opacity = 100;
                break;
            case MotionEvent.ACTION_UP:
                opacity = 30;
                break;
            default:
        }

        invalidate();

        return true;
    }

    //pop animation
    public void show() {

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this,"scaleX",0,1);
        scaleY.setDuration(1000);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this,"scaleY",0,1);
        scaleX.setDuration(1000);


        AnimatorSet helpButtonSet  = new AnimatorSet();
        helpButtonSet.playTogether(scaleX,scaleY);

        helpButtonSet.setInterpolator(new AnticipateInterpolator());

        helpButtonSet.start();


    }
}
