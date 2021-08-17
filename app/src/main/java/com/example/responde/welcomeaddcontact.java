package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeaddcontact extends AppCompatActivity {
Button cancel_btn, save_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeaddcontact);

        save_btn = (Button) findViewById(R.id.welcomesave_btn);
        cancel_btn = (Button) findViewById(R.id.welcomecancel_btn);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewcontact();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });



    }

    public void reviewcontact(){
        Intent intent = new Intent(this, welcomereviewcontactlist.class);
        startActivity(intent);
    }

    public void cancel(){
        Intent intent = new Intent(this, welcomepage.class);
        startActivity(intent);
    }


}