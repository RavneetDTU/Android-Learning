package com.example.ravneet.networkoperations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_download;
    TextView tv_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_download = (Button)findViewById(R.id.btn_download);
        tv_url = (TextView)findViewById(R.id.tv_url);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask(tv_url).execute("http://example.com");
            }
        });
    }
}
