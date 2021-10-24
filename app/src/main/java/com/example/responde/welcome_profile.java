package com.example.responde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class welcome_profile extends AppCompatActivity {

    TextInputLayout name, contact, email, address;
    CheckBox termsAndAgreement;
    TextView ta;
    Button confirm;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    float x1, x2, y1, y2;
    Double lat,lng;
    String status,date;
    SharedPreferences sharedPreferences;

    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String NAME= "name";
    public static final String CONTACT= "contact";
    public static final String EMAIL="email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_profile);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        confirm = findViewById(R.id.save);
        ta = findViewById(R.id.tAnda);
        termsAndAgreement = findViewById(R.id.checkbox);
        lat=null;
        lng=null;
        status="HOME";
        date=null;
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
                validateEmail();
                validateAddress();

                if (!validation()  | !validateEmail() | !validateAddress()) {
                    return;
                }

               else {
               //get all the values
                    String names = name.getEditText().getText().toString();
                    String contacts = contact.getEditText().getText().toString();
                    String emails = email.getEditText().getText().toString();

                    updatebutton();
                    startActivity(new Intent(welcome_profile.this, welcomecontactintro.class));
                    }
            }
        });
    }

    public Boolean terms() {
        if (termsAndAgreement.isChecked()) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean validation() {
        String valName = name.getEditText().getText().toString();

        if (valName.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        }
        else if (valName.length() >= 50) {
            name.setError("Username is too long");
            return false;
        }
        else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateContact() {
        String valContact = contact.getEditText().getText().toString();
        String noWhiteSpaces = "\\A\\w{4,20}\\z";

        if (valContact.isEmpty()) {
            contact.setError("Field cannot be empty");
            return false;
        }
        else if (!valContact.matches(noWhiteSpaces)) {
            contact.setError("No white spaces");
            return false;
        }
        else if (valContact.length() >= 50 || valContact.length() < 11){
            contact.setError("Invalid Contact number");
            return false;
        }
        else {
            contact.setError(null);
            contact.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateEmail() {
        String valEmail = email.getEditText().getText().toString();
        String noWhiteSpaces = "\\A\\w{4,20}\\z";
        String emailPattern = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";

        if (valEmail.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        }
        else if (!valEmail.matches(emailPattern)) {
            email.setError("Invalid Email address");
            return false;
        }
        else if (valEmail.matches(noWhiteSpaces)) {
            email.setError("Invalid Email address");
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateAddress() {
        String valAddress = address.getEditText().getText().toString();

        if (valAddress.isEmpty()) {
            address.setError("Field cannot be empty");
            return false;
        }
        else {
            address.setError(null);
            address.setErrorEnabled(false);
            return true;
        }
    }

    public boolean onTouchEvent(android.view.MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                //swipe right
                if (x1 > x2) {
                    Intent i = new Intent(welcome_profile.this, welcomecontactintro.class);
                    startActivity(i);
                    //swipe left
                } else if (x1 < x2) {
                    Intent i = new Intent(welcome_profile.this, welcomepage.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

    private void updatebutton() {

        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString(NAME, name.getEditText().getText().toString());
        editor.putString(CONTACT, contact.getEditText().getText().toString());
        editor.putString(EMAIL,email.getEditText().getText().toString());
        editor.apply();
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();

    }




}
