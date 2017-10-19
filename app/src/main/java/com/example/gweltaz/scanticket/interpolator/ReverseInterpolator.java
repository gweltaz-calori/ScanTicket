package com.example.gweltaz.scanticket.interpolator;


import android.view.animation.Interpolator;

/**
 * Created by gweltaz on 19/10/2017.
 */

public class ReverseInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float paramFloat) {
        return Math.abs(paramFloat -1f);
    }
}
