package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.common.util.concurrent.HandlerExecutor;

public class splash_screen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean firstTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTime",true);

        if (firstTime){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime",firstTime);
                    editor.apply();

                    Intent i = new Intent(splash_screen.this, welcomepage.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(splash_screen.this, Homepage.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);

            //Intent i = new Intent(splash_screen.this, Homepage.class);
           // startActivity(i);
            //finish();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



    }



}