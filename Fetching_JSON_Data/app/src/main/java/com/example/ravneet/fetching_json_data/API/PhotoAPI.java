package com.example.ravneet.fetching_json_data.API;

import com.example.ravneet.fetching_json_data.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ravneet on 2/7/17.
 */

public interface PhotoAPI {

    @GET("/photos")
    Call<ArrayList<Photo>> getPhotos();

    @GET("albums/{id}/photos")
    Call<ArrayList<Photo>> getPhotosOfID(
            @Path("id") int id
    );
}
