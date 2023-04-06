package com.example.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var08Service extends Service {
    public PracticalTest01Var08Service() {
    }
    ProcessingThread thread;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        thread = new ProcessingThread(getApplicationContext(),intent.getStringExtra("RIDDLESANSWER"));
        thread.start();
        return Service.START_REDELIVER_INTENT;
    }
    @Override
    public void onDestroy() {
        thread.stopThread();
    }
}