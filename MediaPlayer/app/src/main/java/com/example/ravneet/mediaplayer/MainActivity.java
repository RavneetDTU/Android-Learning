package com.example.ravneet.mediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mp = MediaPlayer.create(this,R.raw.video);

        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(this, Uri.parse("https://raw.githubusercontent.com/the-dagger/sample-media/master/audio.mp3"));
            //mp.setDataSource(this,Uri.parse("android.resource://com.example.ravneet.mediaplayer/raw/song"));
            mp.prepareAsync();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.release();
    }
}
