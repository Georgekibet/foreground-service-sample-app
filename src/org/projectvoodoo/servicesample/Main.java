
package org.projectvoodoo.servicesample;

import org.projectvoodoo.foregroundservicesample.R;
import org.projectvoodoo.servicesample.SampleService.SampleLocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;

public class Main extends Activity implements OnClickListener {

    private SampleService sampleService;
    private boolean bound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.button_foreground).setOnClickListener(this);
        findViewById(R.id.button_not_foreground).setOnClickListener(this);

        Intent serviceIntent = new Intent(getApplicationContext(), SampleService.class);
        startService(serviceIntent);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (bound) {

            switch (v.getId()) {
                case R.id.button_not_foreground:
                    sampleService.setAsBackground();
                    break;

                case R.id.button_foreground:
                    sampleService.setAsForeground();
                    break;

                default:
                    break;
            }
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SampleLocalBinder binder = (SampleLocalBinder) service;
            sampleService = binder.getService();
            bound = true;

        }
    };

}
