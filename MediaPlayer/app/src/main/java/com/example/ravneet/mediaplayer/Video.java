package com.example.ravneet.mediaplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView vv = (VideoView) findViewById(R.id.videoView);
        //This is From Internal Storage
        vv.setVideoPath("android.resource://com.example.ravneet.mediaplayer/raw/video");

        //This from an Online Link
        //vv.setVideoPath("https://raw.githubusercontent.com/the-dagger/sample-media/master/video.mp4");

        MediaController controller = new MediaController(this);
        vv.setMediaController(controller);

        vv.start();
    }
}
