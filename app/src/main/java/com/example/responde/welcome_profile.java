package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class welcome_profile extends AppCompatActivity {

    TextInputEditText name, contact, email, address;
    Button confirm;


    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_profile);

        name = (TextInputEditText) findViewById(R.id.name);
        contact = (TextInputEditText)findViewById(R.id.contact);
        address = (TextInputEditText)findViewById(R.id.address);
        email = (TextInputEditText)findViewById(R.id.email) ;
        confirm = findViewById(R.id.save);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference  reference= rootNode.getReference("User");

                //get all the values
                String names = name.getText().toString();
                String contacts = contact.getText().toString();
                String emails = email.getText().toString();
                String addresses = address.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(names, contacts, emails, addresses);

                reference.setValue(helperClass);
            }
        });

    }

    public boolean onTouchEvent(android.view.MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                //swipe right
                if(x1 > x2){
                    Intent i = new Intent(welcome_profile.this, welcomecontactintro.class);
                    startActivity(i);
                    //swipe left
                }else if(x1 < x2){
                    Intent i = new Intent(welcome_profile.this, welcomepage.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
