package com.example.responde;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

        TextView name, eMail, contact, address;
        DatabaseReference reference;
        Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView)findViewById(R.id.name);
        eMail = (TextView)findViewById(R.id.email);
        contact = (TextView)findViewById(R.id.contact);
        address = (TextView)findViewById(R.id.homeAdd);
        update = findViewById(R.id.updateBtn);

        reference = FirebaseDatabase.getInstance().getReference().child("User").child("09954528389");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String dbName = dataSnapshot.child("name").getValue().toString();
                String dbEmail = dataSnapshot.child("email").getValue().toString();
                String dbContact = dataSnapshot.child("contact").getValue().toString();
                String dbHome = dataSnapshot.child("home").getValue().toString();
                name.setText(dbName);
                eMail.setText(dbEmail);
                contact.setText(dbContact);
                address.setText(dbHome);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        //Set Tab selected
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:

                        return true;
                    case R.id.whattodo:
                        startActivity(new Intent(getApplicationContext(),Whattodo.class));
                        overridePendingTransition(0,0);
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
