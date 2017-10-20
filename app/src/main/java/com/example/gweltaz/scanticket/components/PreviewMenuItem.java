package com.example.gweltaz.scanticket.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.gweltaz.scanticket.R;

public class PreviewMenuItem extends RelativeLayout {

    private int backgroundImage;

    public PreviewMenuItem(Context context,int image) {
        super(context);
        this.backgroundImage = image;
        initView();
    }

    public PreviewMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PreviewMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {

        LinearLayout.LayoutParams menuItemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        menuItemParams.weight = 1;
        this.setLayoutParams(menuItemParams);

        ImageButton menuButton = new ImageButton(getContext());
        menuButton.setImageResource(this.backgroundImage);
        menuButton.setBackgroundResource(R.drawable.ripple);

        LayoutParams menuButtonParams = new LayoutParams(80,80);
        menuButtonParams.addRule(CENTER_IN_PARENT,TRUE);
        menuButton.setLayoutParams(menuButtonParams);

        this.addView(menuButton);


    }
}
