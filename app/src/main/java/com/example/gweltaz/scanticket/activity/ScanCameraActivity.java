package com.example.gweltaz.scanticket.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.example.gweltaz.scanticket.R;
import com.example.gweltaz.scanticket.components.AgkitMessage;
import com.example.gweltaz.scanticket.components.HelpButton;
import com.example.gweltaz.scanticket.interfaces.OnStepChangedListener;
import com.example.gweltaz.scanticket.utils.Utils;
import com.example.gweltaz.scanticket.components.ScanButton;

public class ScanCameraActivity extends AppCompatActivity {

    private AgkitMessage helpMessage;
    private ScanButton scanButton;
    private AgkitMessage errorMessage;
    private Button changeStep;
    private HelpButton helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_camera);

        helpMessage = (AgkitMessage) findViewById(R.id.helpMessage);
        scanButton = (ScanButton) findViewById(R.id.scanButton);
        changeStep = (Button) findViewById(R.id.changeStep);
        errorMessage = (AgkitMessage) findViewById(R.id.errorMessage);
        helpButton = (HelpButton) findViewById(R.id.helpButton);

        scanButton.setOnStepChangedListener(new OnStepChangedListener() {
            @Override
            public void onError() {
                errorMessage.animateIn();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        errorMessage.animateOut();
                    }
                }, 2000);
            }

            @Override
            public void onSuccess() {
                Intent previewActivity = new Intent(getApplicationContext(),ScanPreviewActivity.class);
                startActivity(previewActivity);
            }

        });
        changeStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanButton.changeStep();
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"showing help ",Toast.LENGTH_SHORT).show();
            }
        });

        initBubbleHelp();
        showScanButton();
        showHelpButton();

    }

    private void initBubbleHelp() {

        helpMessage.animateIn();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                helpMessage.animateOut();
            }
        }, 5000);
    }

    private void showScanButton() {

        ObjectAnimator translateY = ObjectAnimator.ofFloat(scanButton,"translationY",200,0);
        translateY.setInterpolator(new AnticipateInterpolator());
        translateY.setDuration(1000);
        translateY.start();

    }

    private void showHelpButton() {

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(helpButton,"scaleX",0,1);
        scaleY.setDuration(1000);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(helpButton,"scaleY",0,1);
        scaleX.setDuration(1000);


        AnimatorSet helpButtonSet  = new AnimatorSet();
        helpButtonSet.playTogether(scaleX,scaleY);

        helpButtonSet.setInterpolator(new AnticipateInterpolator());

        helpButtonSet.start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideSystemUI(getWindow());
    }

}
