package com.example.ravneet.permissions.Interface;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by ravneet on 6/7/17.
 */

public class PermissionManager {

    static void  askForPermission(Activity act,String permission, onPermissionRequestListner oprl){

        int permCode = ContextCompat.checkSelfPermission(act,permission);
        if(permCode == PackageManager.PERMISSION_DENIED){

        }
        if(permCode == PackageManager.PERMISSION_GRANTED){

        }

    }

    interface onPermissionRequestListner{
        void onGranted();
        void onDenied();
    }

}