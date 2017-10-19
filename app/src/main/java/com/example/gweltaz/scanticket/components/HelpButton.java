package com.example.gweltaz.scanticket.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.gweltaz.scanticket.stylekit.HelpButtonStyleKit;


public class HelpButton extends View {

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

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
}
