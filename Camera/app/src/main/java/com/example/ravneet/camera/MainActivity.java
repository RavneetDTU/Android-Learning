package com.example.ravneet.camera;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "false";

    Button btn_CLICK;

    Camera c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = Camera.open(0);
        // Here 0 is For Primary Camera

        FrameLayout container = (FrameLayout) findViewById(R.id.frame);

        c.setDisplayOrientation(90);

        //int orientation = getWindowManager().getDefaultDisplay().getOrientation();
        // if int == 0 than it is an

        CameraView cv = new CameraView(this, c);
        container.addView(cv);

        btn_CLICK = (Button) findViewById(R.id.btn_pic);
        btn_CLICK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takepic(view);
            }
        });
    }

    public void getcameraPrperties() {

        List<Camera.Size> pictureSizes = c.getParameters().getSupportedPictureSizes();

        for (Camera.Size s : pictureSizes) {
            Log.d(TAG, "picture Width " + s.width + " picture height " + s.height);
        }

        List<Camera.Size> videoSizes = c.getParameters().getSupportedVideoSizes();

        for (Camera.Size s : videoSizes) {
            Log.d(TAG, "Video Width " + s.width + " Video height " + s.height);
        }

        List<Camera.Size> previewSizes = c.getParameters().getSupportedPreviewSizes();

        for (Camera.Size s : previewSizes) {
            Log.d(TAG, "preview Width " + s.width + " preview height " + s.height);

        }
    }

    public void takepic(View view){

        c.takePicture(null,
                null,
                new Camera.PictureCallback(){
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        c.stopPreview();
                        Log.d(TAG, "onPictureTaken: "+bytes.length);
                        c.startPreview();
                    }
                });

    }
}
