package com.example.ravneet.fetching_json_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ravneet.fetching_json_data.API.CommentAPI;
import com.example.ravneet.fetching_json_data.Adapters.CommentAdapter;
import com.example.ravneet.fetching_json_data.models.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {

    RecyclerView rvComment;
    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_comments);

        rvComment = (RecyclerView) findViewById(R.id.rv_comments);
        rvComment.setLayoutManager(new LinearLayoutManager(this));

        commentAdapter = new CommentAdapter(this,new ArrayList<Comment>());
        rvComment.setAdapter(commentAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommentAPI commentAPI = retrofit.create(CommentAPI.class);

        commentAPI.getComments().enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                commentAdapter.updatecomments(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        });


    }
}
