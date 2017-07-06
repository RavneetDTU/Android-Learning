package com.example.ravneet.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permCode = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permCode == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "Permission Granted");
        }
        if(permCode == PackageManager.PERMISSION_DENIED){
            Log.d(TAG, "Permission Denied");
            ActivityCompat.requestPermissions(this,
                    new String[]
                            {Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    345);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 345){
            for(int i = 0;i< permissions.length;i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "onRequestPermissionsResult: "+permissions[i]+" was Granted");
                }if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    Log.d(TAG, "onRequestPermissionsResult: "+permissions[i]+" was Granted");
                }
            }
        }

    }
}
