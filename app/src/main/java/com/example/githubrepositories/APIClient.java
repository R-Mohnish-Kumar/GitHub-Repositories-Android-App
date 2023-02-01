package com.example.githubrepositories;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static APIClient apiClient;
    APIInterface apiInterface;
    APIClient() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(MainActivity.api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface=retrofit.create(APIInterface.class);
    }
    public static APIClient getApiClient(){
        if(apiClient==null){
            apiClient=new APIClient();
        }
        return apiClient;
    }
}
