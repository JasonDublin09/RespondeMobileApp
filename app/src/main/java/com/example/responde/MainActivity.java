package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextInputLayout userName, userContact, userAddress;
    Button confirmBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.name);
        userContact = findViewById(R.id.contactNum);
        userAddress = findViewById(R.id.homeAdd);
        confirmBtn = findViewById(R.id.confirm);

        //Firebase confirm button
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //get all the value
                String name = userName.getEditText().getText().toString();
                String contact = userContact.getEditText().getText().toString();
                String address = userAddress.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, contact, address);
                reference.child(name).setValue(helperClass);
            }
        });
    }
}