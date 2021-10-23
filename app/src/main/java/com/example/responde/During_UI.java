package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class During_UI extends AppCompatActivity {

    Button backBtn;
    TextView textout;
    StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_during_ui);

        backBtn = findViewById(R.id.back);
        textout = (TextView) findViewById(R.id.whatododuringtext);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(During_UI.this, Whattodo.class));
            }


        });

        }
    }