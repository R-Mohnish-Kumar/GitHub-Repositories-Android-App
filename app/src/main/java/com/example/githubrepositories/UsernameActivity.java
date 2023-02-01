package com.example.githubrepositories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class UsernameActivity extends AppCompatActivity {

    EditText userName;
    Button gotToButton;
    ProgressBar progressBar;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        userName=findViewById(R.id.gitHubUsername);
        gotToButton=findViewById(R.id.goToButton);
        progressBar=findViewById(R.id.progressBar);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        getSupportActionBar().hide();
        gotToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("username",userName.getText().toString());
                startActivity(intent);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPrefManager.isLoggedIn()){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}