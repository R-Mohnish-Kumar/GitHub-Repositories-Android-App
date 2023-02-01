package com.example.githubrepositories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static String api="https://api.github.com";
    ArrayList<RepoModel> ownerData;
    RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        swipeRefreshLayout=findViewById(R.id.swipeRefresh);
        Intent intent=getIntent();
        String userName=intent.getStringExtra("username");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
             recyclerView.setAdapter(new RepoListAdapter(MainActivity.this,sharedPrefManager.getRepoModel()));
                APIClient.getApiClient().apiInterface.getData(userName).enqueue(new Callback<ArrayList<RepoModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<RepoModel>> call, Response<ArrayList<RepoModel>> response) {
                        ownerData= response.body();
                        sharedPrefManager.saveOwner(ownerData);
                        recyclerView.setAdapter(new RepoListAdapter(MainActivity.this,ownerData));
                    }
                    @Override
                    public void onFailure(Call<ArrayList<RepoModel>> call, Throwable t) {
                        Log.e("api","Failure"+t.getLocalizedMessage());
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        if(sharedPrefManager.isLoggedIn()){
            recyclerView.setAdapter(new RepoListAdapter(MainActivity.this,sharedPrefManager.getRepoModel()));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        APIClient.getApiClient().apiInterface.getData(userName).enqueue(new Callback<ArrayList<RepoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RepoModel>> call, Response<ArrayList<RepoModel>> response) {
                ownerData= response.body();
                sharedPrefManager.saveOwner(ownerData);
                Log.e("Check",sharedPrefManager.getRepoModel().toString());
                recyclerView.setAdapter(new RepoListAdapter(MainActivity.this,ownerData));
            }
            @Override
            public void onFailure(Call<ArrayList<RepoModel>> call, Throwable t) {
                Log.e("api","Failure"+t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add:
                Intent intent= new Intent(getApplicationContext(),AddRepoActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                sharedPrefManager.logout();
                Intent intent1= new Intent(getApplicationContext(),UsernameActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

}