package com.example.githubrepositories;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SharedElementCallback;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddRepoActivity extends AppCompatActivity {

    Button createRepoButton;
    EditText repoName,personalAccessToken;
    //String token="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"; // R-Mohnish-Kumar Personal Access Token (GitHub)
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repo);
        createRepoButton=findViewById(R.id.addRepoButton);
        personalAccessToken=findViewById(R.id.personalAccessToken);
        repoName=findViewById(R.id.repoName);
        createRepoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> Reponame=new HashMap<>();
                Reponame.put("name",repoName.getText().toString());
                APIClient.getApiClient().apiInterface.createRepo("Bearer "+personalAccessToken.getText().toString(),Reponame).enqueue(new Callback<CreateRepository>() {
                    @Override
                    public void onResponse(Call<CreateRepository> call, Response<CreateRepository> response) {
                        assert response.body() != null;
                        Log.e("API","Success"+response.body().html_url);
                        finish();
                        Toast.makeText(getApplicationContext(),response.body().name.toString()+"Repository added",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<CreateRepository> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Oops..! Something went wrong",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        getSupportActionBar().hide();
        DisplayMetrics displayMetrics= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width=displayMetrics.widthPixels;
        float height=displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.46));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);
    }
}