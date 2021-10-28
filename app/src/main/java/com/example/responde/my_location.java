package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class my_location extends AppCompatActivity {

    TextInputLayout userName, userContact;
    Button cancel_btn, confirmBtn;
    public String _name,_contact,_address,_email,_contact1,_contact2,_lat,_lng;
    SharedPreferences sharedPreferences;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    Double lat,lng;
    String date;
    TextView address;


    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String NAME= "name";
    public static final String ADDRESS= "address";
    public static final String CONTACT= "contact";
    public static final String EMAIL="email";
    public static final String CONTACT1="contact1";
    public static final String CONTACT2="contact2";
    public static final String LAT="lat";
    public static final String LNG="lng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        userName = findViewById(R.id.name);
        userContact = findViewById(R.id.contactNum);
        address = findViewById(R.id.address);
        confirmBtn = findViewById(R.id.confirm);
        cancel_btn = (Button) findViewById(R.id.cancel_btn);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        date= dateformat.format(calendar.getTime());

        confirmBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("IncomingReport");

                String y = _contact1;
                String z = _contact2;
                String[] x= {y,z};
                List<String> tag= Arrays.asList(x);

                String option = "HOME";
                String status = "";
                String name = _name;
                String contact= _contact;
                String email = _email;
                String address = _address;

                lat= Double.parseDouble(_lat);
                lng = Double.parseDouble(_lng);


                UserHelperClass helperClass = new UserHelperClass(name, contact, email, address,lat,lng,option,date,status/*, (ArrayList<String>) tag*/, tag);
                reference.push().setValue(helperClass);
                Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_SHORT).show();

            }
        }));
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomepage();
            }
        });

        loadData();
        updateViews();
    }

    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }


    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        _name= sharedPreferences.getString(NAME,"");
        _contact= sharedPreferences.getString(CONTACT,"");
        _address= sharedPreferences.getString(ADDRESS,"");
        _email= sharedPreferences.getString(EMAIL,"");
        _contact1= sharedPreferences.getString(CONTACT1,"");
        _contact2= sharedPreferences.getString(CONTACT2,"");
        _lat = sharedPreferences.getString(LAT,"");
        _lng = sharedPreferences.getString(LNG,"");
    }
    public void updateViews(){
        userName.getEditText().setText(_name);
        userContact.getEditText().setText("+63"+_contact);
        address.setText(_address);
    }

    public void displayToastMsg(View v) {
        toastMsg("Report Sent to the Fire District V");
    }

}