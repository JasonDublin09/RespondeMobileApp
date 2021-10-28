package com.example.responde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class Contacts extends AppCompatActivity {

    TextInputLayout name1, name2, contact1, contact2;
    Button confirm;
    public String _name1,_contact1,_name2,_contact2;

    SharedPreferences sharedPreferences;

    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String EMCONTACTNAME1="emname1";
    public static final String EMCONTACTNAME2="emname2";
    public static final String CONTACT1="contact1";
    public static final String CONTACT2="contact2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        name1 = findViewById(R.id.name1);
        contact1 = findViewById(R.id.contact1);
        name2 = findViewById(R.id.name2);
        contact2 = findViewById(R.id.contact2);
        confirm = findViewById(R.id.contactBtn2);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                validateContact1();
                validateContact2();
                validateName1();
                validateName2();

                if (!validateContact1()  | !validateContact2() | !validateName1() | !validateName2()) {
                    return;
                }
                else {
                    //get all the values
                    editor.putString(EMCONTACTNAME1, name1.getEditText().getText().toString());
                    editor.putString(EMCONTACTNAME2, name2.getEditText().getText().toString());
                    editor.putString(CONTACT1,contact1.getEditText().getText().toString());
                    editor.putString(CONTACT2, contact2.getEditText().getText().toString());
                    editor.apply();
                    loadData();
                    updateViews();
                }
            }

        });
        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        //Set Tab selected
        bottomNavigationView.setSelectedItemId(R.id.contacts);
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
                        startActivity(new Intent(getApplicationContext(),Homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.contacts:

                        return true;
                }
                return false;
            }
        });
        loadData();
        updateViews();
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
        else if (valContact.length() >= 50 || valContact.length() < 10){
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
        else if (valContact.length() >= 50 || valContact.length() < 10){
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
        Toast.makeText(this,"Your information has been updated",Toast.LENGTH_SHORT).show();
    }

}