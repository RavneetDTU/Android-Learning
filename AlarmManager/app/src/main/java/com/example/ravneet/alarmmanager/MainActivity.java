package com.example.ravneet.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("A","abc");
        PendingIntent pi = PendingIntent.getActivity(this,112,i,PendingIntent.FLAG_ONE_SHOT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

        }
        am.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+(1000*60),pi);
    }
}
