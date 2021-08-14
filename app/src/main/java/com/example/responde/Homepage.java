package com.example.responde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {

    private Object menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationbar);

       bottomNavigationView.setSelectedItemId(R.id.home);

       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.profile:
                       startActivity(new Intent(getApplicationContext(),
                               Profile.class));
                       overridePendingTransition(0,0);
                       return true;
                   case R.id.whattodo:
                       startActivity(new Intent(getApplicationContext(),
                               DashBoard.class));
                       overridePendingTransition(0,0);
                       return true;
                   case R.id.home:
                       startActivity(new Intent(getApplicationContext(),
                               Home.class));
                       overridePendingTransition(0,0);
                       return true;
               }

               return false;
           }
       });



    }
}