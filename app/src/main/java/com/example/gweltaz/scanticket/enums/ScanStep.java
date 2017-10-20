package com.example.gweltaz.scanticket.enums;

import android.graphics.Color;

/**
 * Created by gweltaz on 20/10/2017.
 */

public enum ScanStep {

    READY(Color.argb(255, 139, 195, 74),400),
    ERROR(Color.argb(100, 239, 83, 80),1000),
    PROCESSING(Color.argb(100, 255, 193, 7),400),
    MANUAL(Color.argb(255, 255, 255, 255),400);

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