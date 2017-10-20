package com.example.gweltaz.scanticket.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;

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

    private boolean isHelpShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_camera);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
                initBubbleHelp();
            }
        });

        initBubbleHelp();
        scanButton.show();
        helpButton.show();

    }

    private void initBubbleHelp() {

        if(isHelpShowing) {
            return;
        }

        isHelpShowing = true;

        helpMessage.animateIn();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                helpMessage.animateOut();
                isHelpShowing = false;
            }
        }, 5000);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideSystemUI(getWindow());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.camera_top,menu);
        return true;
    }

}
