package com.example.ravneet.fetching_json_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ravneet.fetching_json_data.API.AlbumAPI;
import com.example.ravneet.fetching_json_data.Adapters.AlbumAdapter;
import com.example.ravneet.fetching_json_data.models.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumActivity extends AppCompatActivity {

    public static final String TAG = "Hello";


    RecyclerView rvalbum;
    AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_album);


        rvalbum = (RecyclerView) findViewById(R.id.rv_album);
        rvalbum.setLayoutManager(new LinearLayoutManager(this));

        albumAdapter = new AlbumAdapter(this, new ArrayList<Album>());
        rvalbum.setAdapter(albumAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlbumAPI albumAPI = retrofit.create(AlbumAPI.class);

        albumAPI.getAlbum().enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                Log.d("Error", "onResponse: "+response.body().size());
                albumAdapter.updatealbum(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                Log.d("Error", "onFailure: ");
            }
        });
    }
}
