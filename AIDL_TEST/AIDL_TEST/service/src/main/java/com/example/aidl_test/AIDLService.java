package com.example.aidl_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AIDLService extends Service {

    //定义Service ACTION，在AndroidManifest.xml中注册
    public static final String ACTION = "com.android.service.AIDLService";

    private static final String TAG = "AIDLService";
    private MyBinder myBinder;
    public AIDLService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand....");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        Log.i(TAG,"onBind");
        myBinder = new MyBinder();
        return myBinder;
    }

    public class MyBinder extends IMyService.Stub{

        @Override
        public void play() throws RemoteException {
            Log.i(TAG,"Service自定义play()...");
        }

        @Override
        public void pause() throws RemoteException {
            Log.i(TAG,"Service自定义pause()...");
        }

    }
}