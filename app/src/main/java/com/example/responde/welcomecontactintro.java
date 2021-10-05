package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class welcomecontactintro extends AppCompatActivity implements View.OnClickListener{

    float x1, x2, y1, y2;
    TextInputLayout name1, name2, contact1, contact2;
    Button contactbtn;
    public String _name1,_contact1,_name2,_contact2;

    SharedPreferences sharedPreferences;

    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String EMCONTACTNAME1="emname1";
    public static final String EMCONTACTNAME2="emname1";
    public static final String CONTACT1="contact1";
    public static final String CONTACT2="contact2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomecontactintro);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        contactbtn = findViewById(R.id.contactBtn);
        name1 = findViewById(R.id.name1);
        contact1 = findViewById(R.id.contact1);
        name2 = findViewById(R.id.name2);
        contact2 = findViewById(R.id.contact2);


        //String[] x= {y,z};
        //List<String> tag= Arrays.asList(x);

        contactbtn.setOnClickListener(this);

        loadData();
        updateViews();

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

    private void updatebutton() {

        SharedPreferences.Editor editor= sharedPreferences.edit();

        String x= String.valueOf(contact1.getEditText().getText());

        editor.putString(EMCONTACTNAME1, name1.getEditText().getText().toString());
        editor.putString(EMCONTACTNAME2, name2.getEditText().getText().toString());
        editor.putString(CONTACT1,contact1.getEditText().getText().toString());
        editor.putString(CONTACT2, contact2.getEditText().getText().toString());
        editor.apply();
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.contactBtn:
                updatebutton();
                //Toast.makeText(this,x,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        _name1= sharedPreferences.getString(EMCONTACTNAME1,"");
        _contact1= sharedPreferences.getString(CONTACT1,"");
        _name2= sharedPreferences.getString(EMCONTACTNAME2,"");
        _contact2= sharedPreferences.getString(CONTACT2,"");

    }
    public void updateViews(){
        name1.getEditText().setText(_name1);
        contact1.getEditText().setText(_contact1);
        name2.getEditText().setText(_name2);
        contact2.getEditText().setText(_contact2);
    }
}