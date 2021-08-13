package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Homepage extends AppCompatActivity {
private ImageButton myloc_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        myloc_btn = (ImageButton) findViewById(R.id.myloc_btn);
        myloc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmy_location();
            }
        });

    }

    public void openmy_location(){
        Intent intent = new Intent(this, my_location.class);
        startActivity(intent);
    }
}