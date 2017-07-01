package com.example.ravneet.fetching_json_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ravneet.fetching_json_data.API.UserAPI;
import com.example.ravneet.fetching_json_data.Adapters.UserAdapter;
import com.example.ravneet.fetching_json_data.models.User;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {

    RecyclerView rvUserList;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        rvUserList = (RecyclerView)findViewById(R.id.rv_user);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(this,new ArrayList<User>());
        rvUserList.setAdapter(userAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI.

    }
}
