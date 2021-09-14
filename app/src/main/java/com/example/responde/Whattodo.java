package com.example.responde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Whattodo extends AppCompatActivity {

    ImageView buttonAfter, buttonBefore, buttonDuring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whattodo);

        buttonAfter = findViewById(R.id.after);
        buttonBefore = findViewById(R.id.before);
        buttonDuring = findViewById(R.id.during);

        buttonBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Whattodo.this, beforeUI.class));
            }
        });

        buttonDuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Whattodo.this, During_UI.class));
            }
        });
        buttonAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Whattodo.this, after_UI.class));
            }
        });
        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        //Set Tab selected
        bottomNavigationView.setSelectedItemId(R.id.whattodo);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.whattodo:

                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.contacts:
                        startActivity(new Intent(getApplicationContext(),Contacts.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}