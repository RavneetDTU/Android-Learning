package com.example.ravneet.fetching_json_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.ravneet.fetching_json_data.R.id.img_view_album;

public class AlbumActivity extends AppCompatActivity {

    ImageView img_album1;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        img_album1 = (ImageView) findViewById(R.id.img_view_album);

        Picasso.with(getApplicationContext()).load("Image URL").into(img_album1);
    }
}
