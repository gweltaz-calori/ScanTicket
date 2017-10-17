package com.example.gweltaz.scanticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gweltaz.scanticket.R;
import com.example.gweltaz.scanticket.utils.Utils;
import com.example.gweltaz.scanticket.view.ScanButton;

public class ScanCameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_camera);
        ScanButton scanButton = (ScanButton) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previewActivity = new Intent(getApplicationContext(),ScanPreviewActivity.class);
                startActivity(previewActivity);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideSystemUI(getWindow());
    }

}
