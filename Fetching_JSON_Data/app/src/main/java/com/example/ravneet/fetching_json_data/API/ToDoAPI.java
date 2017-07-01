package com.example.ravneet.fetching_json_data.API;

import com.example.ravneet.fetching_json_data.models.ToDo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ravneet on 1/7/17.
 */

public interface ToDoAPI {

    @GET("/todos")
    Call<ArrayList<ToDo>> gettasks();

}
