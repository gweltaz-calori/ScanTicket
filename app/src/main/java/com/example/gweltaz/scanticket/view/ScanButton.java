package com.example.gweltaz.scanticket.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.example.gweltaz.scanticket.stylekit.ShotButtonStyleKit;


/**
 * Created by gweltaz on 16/10/2017.
 */

public class ScanButton extends View {



    private int arcRotation = 0;

    public ScanButton(Context context) {
        super(context);
    }

    public ScanButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScanButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ShotButtonStyleKit.drawShotButton(canvas,new RectF(0,0,getWidth(),getHeight()),ShotButtonStyleKit.ResizingBehavior.AspectFit,arcRotation);

        arcRotation+= 10;

        if(arcRotation > 360) {
            arcRotation = 0;
        }

        invalidate();

    }

}
