package com.example.githubrepositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String SHARED_PREF_NAME="github";

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveOwner(ArrayList<RepoModel> repoModel) {
        sharedPreferences= context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        Gson gson= new Gson();
        String json=gson.toJson(repoModel);
        editor.putString("OwnerData",json);
        editor.putBoolean("logged",true);
//        editor.putLong("id",repoModel.getId());
//        editor.putString("name",repoModel.getName());
//        editor.putString("full_name",repoModel.getFull_name());
//        editor.putString("html_url",repoModel.getHtml_url());
        editor.apply();
    }

    public ArrayList<RepoModel> getRepoModel(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("OwnerData", "");
        Type type = new TypeToken<List<RepoModel>>() {}.getType();
        return gson.fromJson(json,type);
    }

    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }
    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
