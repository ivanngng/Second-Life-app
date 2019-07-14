package com.ivanng.secondlifeapp;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ivanng.secondlifeapp.Login.LoginActivity;

public class LoadingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        SystemClock.sleep(2000);
        Intent loadingIntent = new Intent(LoadingPage.this, LoginActivity.class);
        startActivity(loadingIntent);
        finish();
    }
}
