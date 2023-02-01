package com.example.githubrepositories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent=getIntent();
        Bundle data=intent.getExtras();
        String url=data.getString("url");
        webView= findViewById(R.id.webView);
        webView.loadUrl(url);
    }
}