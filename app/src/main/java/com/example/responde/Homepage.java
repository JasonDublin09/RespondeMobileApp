package com.example.responde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {
private ImageButton myloc_btn;
private ImageButton otherloc_btn;
private Button shortcut_btn,splashscreen_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        shortcut_btn = (Button) findViewById(R.id.shortroute);

        shortcut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_location();
            }
        });

        otherloc_btn = (ImageButton) findViewById(R.id.otherloc_btn);
        otherloc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openotherloc();
            }
        });

        myloc_btn = (ImageButton) findViewById(R.id.myloc_btn);
        myloc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmy_location();
            }
        });

        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        //Set Tab selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.whattodo:
                        startActivity(new Intent(getApplicationContext(),Whattodo.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:

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

    public void openmy_location(){
        Intent intent = new Intent(this, my_location.class);
        startActivity(intent);
    }
    public void openotherloc(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void my_location(){
        Intent intent = new Intent(this, welcomepage.class);
        startActivity(intent);
    }

}