package com.example.gweltaz.scanticket.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.gweltaz.scanticket.R;
import com.example.gweltaz.scanticket.components.BubbleHelp;
import com.example.gweltaz.scanticket.utils.Utils;
import com.example.gweltaz.scanticket.components.ScanButton;

public class ScanCameraActivity extends AppCompatActivity {

    private BubbleHelp bubbleHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_camera);

        bubbleHelp = (BubbleHelp) findViewById(R.id.helpMessage);


        final ScanButton scanButton = (ScanButton) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previewActivity = new Intent(getApplicationContext(),ScanPreviewActivity.class);
                startActivity(previewActivity);
            }
        });

        Button changeStep = (Button) findViewById(R.id.changeStep);
        changeStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanButton.changeStep();


                // testing purpose only
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(bubbleHelp,"translationY",-500,0);
                scaleY.setDuration(400);
                ObjectAnimator.setFrameDelay(10);
                scaleY.setInterpolator(new LinearInterpolator());
                scaleY.start();


            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideSystemUI(getWindow());
    }

}
