package com.example.gweltaz.scanticket.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    private static final int CAPTURE_AUTO = 1;
    private static final int CAPTURE_MANUAL = 0;

    private boolean isHelpShowing = false;
    private int captureMode = CAPTURE_AUTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_camera);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        helpMessage = (AgkitMessage) findViewById(R.id.helpMessage);
        scanButton = (ScanButton) findViewById(R.id.scanButton);
        changeStep = (Button) findViewById(R.id.changeStep);
        errorMessage = (AgkitMessage) findViewById(R.id.errorMessage);
        helpButton = (HelpButton) findViewById(R.id.helpButton);

        // Listener pour savoir quand l'état du scan button change
        scanButton.setOnStepChangedListener(new OnStepChangedListener() {
            @Override
            public void onError() {
                showErrorMessage();
            }

            @Override
            public void onSuccess() {
                // we can open new intent
                Intent previewActivity = new Intent(getApplicationContext(),ScanPreviewActivity.class);
                startActivity(previewActivity);
            }

        });

        //only for testing purposes
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

        scanButton.show();
        helpButton.show();

    }

    private void initBubbleHelp() {
        //avoid spamming help button
        if(isHelpShowing) {
            return;
        }

        isHelpShowing = true;

        helpMessage.animateIn(); //show the help message

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                helpMessage.animateOut();
                isHelpShowing = false;
            }
        }, 5000); //hide again after 5 seconds
    }

    private void showErrorMessage() {
        errorMessage.animateIn(); //appear
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                errorMessage.animateOut();
            }
        }, 2000); //disappear after 2 seconds
    }


    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideSystemUI(getWindow()); //go full screen
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.camera_top,menu);

        final MenuItem automaticMenuItem = menu.findItem(R.id.action_automatic);
        final View actionView = automaticMenuItem.getActionView(); //retrieve custom view for this menu item

        //handle click here because it's a custom action layout https://developer.android.com/guide/topics/resources/menu-resource.html
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView automaticText = actionView.findViewById(R.id.automatic_text); //retrieve the text view inside the custom view
                if(captureMode == CAPTURE_AUTO) {
                    automaticText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                    captureMode = CAPTURE_MANUAL;
                }
                else {
                    automaticText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.orange));
                    captureMode = CAPTURE_AUTO;
                }
            }
        });

        return true;
    }


}


