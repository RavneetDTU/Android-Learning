package com.example.ravneet.fetching_json_data.API;

import com.example.ravneet.fetching_json_data.models.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ravneet on 2/7/17.
 */

public interface CommentAPI {

    @GET("/comments")
    Call<ArrayList<Comment>> getComments();

    @GET("/comments")
    Call<ArrayList<Comment>> getCommentByPostId(
            @Query("postid") int postId
    );
}
