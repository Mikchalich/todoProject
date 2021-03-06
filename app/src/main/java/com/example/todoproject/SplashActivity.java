package com.example.todoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        new Handler().postDelayed(() -> {
                startActivity(intent);
                finish();
        }, 1000);
    }
}