
package org.projectvoodoo.servicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SampleService extends Service {

    private final static String TAG = "Voodoo ForegroundServiceSample MyService";

    private final IBinder binder = new SampleLocalBinder();

    public class SampleLocalBinder extends Binder {
        SampleService getService() {
            return SampleService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");

        super.onDestroy();
    }

    public void setAsForeground() {
        startForeground(Notif.notifId, Notif.getNotification(getApplicationContext()));
    }

    public void setAsBackground() {
        stopForeground(true);
    }

}
