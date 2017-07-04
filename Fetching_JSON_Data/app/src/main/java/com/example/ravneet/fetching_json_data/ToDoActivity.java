package com.example.ravneet.fetching_json_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ravneet.fetching_json_data.API.ToDoAPI;
import com.example.ravneet.fetching_json_data.Adapters.ToDoAdapter;
import com.example.ravneet.fetching_json_data.models.ToDo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToDoActivity extends AppCompatActivity {

    public static final String TAG = "Hello";

    RecyclerView rvtodo;
    ToDoAdapter toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_todo);



        rvtodo = (RecyclerView) findViewById(R.id.rv_todo);
        rvtodo.setLayoutManager(new LinearLayoutManager(this));

        toDoAdapter = new ToDoAdapter(this,new ArrayList<ToDo>());
        rvtodo.setAdapter(toDoAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ToDoAPI toDoAPI = retrofit.create(ToDoAPI.class);

        final Callback<ArrayList<ToDo>> todoCallback = new Callback<ArrayList<ToDo>>() {
            @Override
            public void onResponse(Call<ArrayList<ToDo>> call, Response<ArrayList<ToDo>> response) {
                Log.i(TAG, "onResponse: ");
                toDoAdapter.updatetasks(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ToDo>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t);
            }
        };

//        toDoAPI.gettasks().enqueue(new Callback<ArrayList<ToDo>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ToDo>> call, Response<ArrayList<ToDo>> response) {
//                toDoAdapter.updatetasks(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ToDo>> call, Throwable t) {
//
//            }
//        });

        int userIdRecived = getIntent().getIntExtra("userId",-1);
        if(userIdRecived != -1){
            toDoAPI.getTodosbypostid(userIdRecived).enqueue(todoCallback);
        }else {
            toDoAPI.gettasks().enqueue(todoCallback);
        }
    }
}
