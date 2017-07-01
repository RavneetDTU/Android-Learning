package com.example.ravneet.fetching_json_data.API;

import com.example.ravneet.fetching_json_data.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ravneet on 1/7/17.
 */

public interface UserAPI {
    @GET("/users")
    Call<ArrayList<User>> getUsers();
}
