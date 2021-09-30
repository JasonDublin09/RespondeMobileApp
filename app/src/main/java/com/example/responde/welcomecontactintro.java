package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class welcomecontactintro extends AppCompatActivity {

    float x1, x2, y1, y2;
    TextInputLayout name1, name2, contact1, contact2;
    Button contactbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomecontactintro);

        contactbtn = findViewById(R.id.contactBtn);
        name1 = findViewById(R.id.name_1);
        contact1 = findViewById(R.id.contact_1);
        name2 = findViewById(R.id.name_2);
        contact2 = findViewById(R.id.contact_2);

        String y=contact1.getEditText().getText().toString();
        String z=contact2.getEditText().getText().toString();
        String[] x= {y,z};
        List<String> tag= Arrays.asList(x);

        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("Emergencycontact");
                reference.push().setValue(y);
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
                //swipe left
                if(x1 > x2){
                    Intent i = new Intent(welcomecontactintro.this, welcomelastpage.class);
                    startActivity(i);
                    //swipe right
                }else if(x1 < x2){
                    Intent i = new Intent(welcomecontactintro.this, welcome_profile.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

}