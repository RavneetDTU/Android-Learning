package com.example.ravneet.fetching_json_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ravneet.fetching_json_data.API.PhotoAPI;
import com.example.ravneet.fetching_json_data.API.PostAPI;
import com.example.ravneet.fetching_json_data.Adapters.PhotoAdapter;
import com.example.ravneet.fetching_json_data.Adapters.PostAdapter;
import com.example.ravneet.fetching_json_data.models.Photo;
import com.example.ravneet.fetching_json_data.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoActivity extends AppCompatActivity {

    RecyclerView rvphotos;
    PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_photos);

        rvphotos = (RecyclerView) findViewById(R.id.rv_photo);
        rvphotos.setLayoutManager(new LinearLayoutManager(this));

        photoAdapter = new PhotoAdapter(this, new ArrayList<Photo>());
        rvphotos.setAdapter(photoAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoAPI photoAPI = retrofit.create(PhotoAPI.class);

        photoAPI.getPhotos().enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                photoAdapter.updatephotos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        });
    }
}
