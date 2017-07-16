package com.example.ravneet.firebasecb;

import android.app.Notification;
import android.app.NotificationManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ravneet on 16/7/17.
 */

public class MyMessagingService extends FirebaseMessagingService {

    public static final String TAG = "FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: "+remoteMessage.getMessageId());
        Log.d(TAG, "onMessageReceived: "+remoteMessage.getMessageType());
        Log.d(TAG, "onMessageReceived: "+remoteMessage.getData());
        Log.d(TAG, "onMessageReceived: "+remoteMessage.getNotification().getTitle());
        Log.d(TAG, "onMessageReceived: "+remoteMessage.getNotification().getBody());

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        Notification myNotification = new Notification.Builder(this).setContentTitle(remoteMessage.getNotification().getTitle()).setContentText(remoteMessage.getNotification().getBody()).set

    }
}
