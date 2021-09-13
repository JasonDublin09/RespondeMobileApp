package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class welcome_profile extends AppCompatActivity {

    float x1, x2, y1, y2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_profile);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        confirm = findViewById(R.id.save);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("User");
                validation();
                validateContact();
                validateEmail();
                validateAddress();

                if (!validation() | !validateContact() | !validateEmail() | !validateAddress()) {
                    return;
                }
                else {
                    //get all the values
                    String names = name.getEditText().getText().toString();
                    String contacts = contact.getEditText().getText().toString();
                    String emails = email.getEditText().getText().toString();
                    String addresses = address.getEditText().getText().toString();
                    Double lat = null;
                    Double lng = null;
                    UserHelperClass helperClass = new UserHelperClass(names, contacts, emails, addresses,lat,lng);
                    reference.child(contacts).setValue(helperClass);
                }
            }
        });
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
