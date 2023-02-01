package com.example.githubrepositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("/users/{user}/repos")
    Call <ArrayList<RepoModel>> getData(@Path("user") String username);


    @POST("/user/repos")
    Call<CreateRepository> createRepo(@Header("Authorization")String key,@Body HashMap<String,String> name);

}
