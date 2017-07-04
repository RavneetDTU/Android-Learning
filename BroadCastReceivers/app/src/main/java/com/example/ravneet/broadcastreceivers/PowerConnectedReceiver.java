package com.example.ravneet.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

public class PowerConnectedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(intent.getAction().equals(intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context,"Charger is Connected",Toast.LENGTH_SHORT).show();

        if(intent.getAction().equals(intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context,"Power is Disconnected",Toast.LENGTH_SHORT).show();
        }


        }

    }
}
