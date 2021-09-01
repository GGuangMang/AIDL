package com.example.aidl_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startServiceButton;
    Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = findViewById(R.id.start_service);
        mContext = this.getApplicationContext();

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /*ComponentName componentName = new ComponentName("com.example.aidl_test","com.example.aidl_test.AIDLService");
                intent.setComponent(componentName);*/
                intent.setPackage("com.example.aidl_test");
                intent.setAction("com.android.service.AIDLService");
                mContext.startService(intent);
            }
        });
    }
}