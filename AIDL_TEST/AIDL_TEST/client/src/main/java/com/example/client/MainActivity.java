package com.example.client;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.SyncStateContract;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aidl_test.IMyService;

import Constant.ACTION_AIDL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "AIDLClient";
    Button  btnBind,btnPlay,btnPause;
    IMyService mBinder;
    boolean mIsBind = false;

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /*
             * 获得另一个进程中的Service传递过来的IBinder对象-service，
             * 用IMyService.Stub.asInterface方法转换该对象，这点与进程内的通信不同
             */
            Log.i(TAG, "onServiceConnected....");
            mBinder = IMyService.Stub.asInterface(service);
            mIsBind = true;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBind = (Button) findViewById(R.id.butBind);
        btnPlay = (Button) findViewById(R.id.butPlay);
        btnPause = (Button) findViewById(R.id.butPause);

        btnBind.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        int btn = view.getId();
        switch (btn){
            case R.id.butBind:
                Log.i(TAG,"btnBind========" + "mIsBind = " + mIsBind);
                intent.setPackage("com.example.aidl_test");
                intent.setAction("com.android.service.AIDLService");
                bindService(intent, mConn, BIND_AUTO_CREATE);

                break;
            case R.id.butPlay:
                Log.i(TAG,"play()========" + "mIsBind = " + mIsBind);
                if (mIsBind){
                    Log.i(TAG,"play()");
                    try {
                        mBinder.play();
                    }catch (RemoteException e){
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.butPause:
                Log.i(TAG,"pause()");
                if (mIsBind){
                    Log.i(TAG,"pause()");
                    try {
                        mBinder.pause();
                    }catch (RemoteException e){
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}