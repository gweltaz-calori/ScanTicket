package com.example.gweltaz.scanticket.components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by gweltaz on 19/10/2017.
 */

public class BubbleHelp extends TextView {
    public BubbleHelp(Context context) {
        super(context);
        initView();
    }

    public BubbleHelp(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BubbleHelp(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public BubbleHelp(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),"Muli-Regular.ttf");
        this.setTypeface(font);
    }

}
