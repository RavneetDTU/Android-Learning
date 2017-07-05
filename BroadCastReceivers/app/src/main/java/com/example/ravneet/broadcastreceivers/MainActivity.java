package com.example.ravneet.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    MyReceiver receiver;
    FrameLayout frameLayout;

    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        receiver = new MyReceiver();
//        intentFilter = new IntentFilter();
//        frameLayout = (FrameLayout) findViewById(R.id.mainFrame);
//        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
//        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
//        registerReceiver(receiver,intentFilter);

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };






    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(intent.ACTION_POWER_CONNECTED)){
                frameLayout.setBackgroundColor(Color.GREEN);
            }
            if(intent.getAction().equals(intent.ACTION_POWER_DISCONNECTED)){
                frameLayout.setBackgroundColor(Color.RED);
            }

        }
    }
}
