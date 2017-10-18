package com.example.gweltaz.scanticket.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.gweltaz.scanticket.R;

/**
 * Created by gweltaz on 17/10/2017.
 */

public class PreviewMenu extends LinearLayout {
    public PreviewMenu(Context context) {
        super(context);
        initView();
    }

    public PreviewMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PreviewMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public PreviewMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {

        LayoutParams previewActionsParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(previewActionsParams);

        PreviewMenuItem editMenu = new PreviewMenuItem(getContext(), R.drawable.ic_picture_filter);
        PreviewMenuItem cropMenu = new PreviewMenuItem(getContext(),R.drawable.ic_crop);

        this.addView(editMenu);
        this.addView(cropMenu);

    }

}
