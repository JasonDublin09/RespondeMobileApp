package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

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
        name2 = findViewById(R.id.sName);
        contact2 = findViewById(R.id.contact2);

        //String[] x= {y,z};
        //List<String> tag= Arrays.asList(x);

        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateContact1();
                validateContact2();
                validateName1();
                validateName2();

                if (!validateContact1()  | !validateContact2() | !validateName1() | validateName2()) {
                    return;
                }
                else {
                    //get all the values
                    String names1 = name1.getEditText().getText().toString();
                    String names2 = name2.getEditText().getText().toString();
                    String contacts1 = contact1.getEditText().getText().toString();
                    String contacts2 = contact2.getEditText().getText().toString();
                    loadData();
                    updateViews();
                    startActivity(new Intent(welcomecontactintro.this, welcomelastpage.class));
                }
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
    public Boolean validateContact1() {
        String valContact = contact1.getEditText().getText().toString();
        String noWhiteSpaces = "\\A\\w{4,20}\\z";

        if (valContact.isEmpty()) {
            contact1.setError("Field cannot be empty");
            return false;
        }
        else if (!valContact.matches(noWhiteSpaces)) {
            contact1.setError("No white spaces");
            return false;
        }
        else if (valContact.length() >= 50 || valContact.length() < 11){
            contact1.setError("Invalid Contact number");
            return false;
        }
        else {
            contact1.setError(null);
            contact1.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean validateContact2() {
        String valContact = contact2.getEditText().getText().toString();
        String noWhiteSpaces = "\\A\\w{4,20}\\z";

        if (valContact.isEmpty()) {
            contact2.setError("Field cannot be empty");
            return false;
        }
        else if (!valContact.matches(noWhiteSpaces)) {
            contact2.setError("No white spaces");
            return false;
        }
        else if (valContact.length() >= 50 || valContact.length() < 11){
            contact2.setError("Invalid Contact number");
            return false;
        }
        else {
            contact2.setError(null);
            contact2.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean validateName1() {
        String valName = name1.getEditText().getText().toString();

        if (valName.isEmpty()) {
            name1.setError("Field cannot be empty");
            return false;
        }
        else if (valName.length() >= 50) {
            name1.setError("Username is too long");
            return false;
        }
        else {
            name1.setError(null);
            name1.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean validateName2() {
        String valName = name2.getEditText().getText().toString();

        if (valName.isEmpty()) {
            name2.setError("Field cannot be empty");
            return false;
        }
        else if (valName.length() >= 50) {
            name2.setError("Username is too long");
            return false;
        }
        else {
            name2.setError(null);
            name2.setErrorEnabled(false);
            return true;
        }
    }
}