package com.example.responde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Profile extends AppCompatActivity implements View.OnClickListener{


    private TextInputLayout name,contact,email, address1;
    private TextView address;
    public String _name,_contact,_address,_email;
    SharedPreferences sharedPreferences;
    FusedLocationProviderClient fusedLocationProviderClient;
    Double lat, lng;
    String date;

    String userName;
    DatabaseReference reference;
    private Button button1,button2;

    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String NAME= "name";
    public static final String ADDRESS= "address";
    public static final String CONTACT= "contact";
    public static final String EMAIL="email";
    public static final String LAT="lat";
    public static final String LNG="lng";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        //ini fusedLocationProvider
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Profile.this);

        name =  findViewById(R.id.name1);
        contact = findViewById(R.id.contact1);
        email = findViewById(R.id.email1);
        button1 = findViewById(R.id.updateBtn);
        button2 = (Button) findViewById(R.id.getlcn);
        address = findViewById(R.id.addressedit);
        address1 = findViewById(R.id.address);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

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

        loadData();
        updateViews();
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        //ini location manager
        LocationManager locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE
        );
        //check Condition
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //When location service is ena
            //get last location
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    //ini location

                    Location location = task.getResult();
                    //check condition
                    if (location == null) {
                        //when location is null
                        try {
                            //ini geocoder
                            Geocoder geocoder= new Geocoder(Profile.this,
                                    Locale.getDefault());
                            //get address
                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(),location.getLongitude(),1
                            );

                            address.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
                            lat= location.getLatitude();
                            lng= location.getLongitude();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //when location is not null
                        //ini location request
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        //ini location callback
                        LocationCallback locationCallback= new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                try {
                                    //ini geocoder
                                    Geocoder geocoder = new Geocoder(Profile.this,
                                            Locale.getDefault());
                                    //get address
                                    List<Address> addresses = geocoder.getFromLocation(
                                            location1.getLatitude(), location1.getLongitude(), 1
                                    );

                                    //set address
                                    address.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
                                    lat= location.getLatitude();
                                    lng= location.getLongitude();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        //request location updates
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                                locationCallback, Looper.myLooper());
                    }
                }
            });
        } else {
            //when location service is not ena
            //open location settings
            Toast.makeText(getApplicationContext(), "Please Enable Location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    private void updatebutton() {
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString(NAME, name.getEditText().toString());
        editor.putString(CONTACT, contact.getEditText().toString());
        editor.putString(ADDRESS, address1.getEditText().toString());
        editor.putString(ADDRESS, address.getText().toString());
        editor.putString(EMAIL,email.getEditText().toString());
        editor.putString(LAT,lat.toString());
        editor.putString(LNG,lng.toString());

        editor.apply();
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();

    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        _name= sharedPreferences.getString(NAME,"");
        _contact= sharedPreferences.getString(CONTACT,"");
        _address= sharedPreferences.getString(ADDRESS,"");
        _email= sharedPreferences.getString(EMAIL,"");
    }
    public void updateViews(){
        name.getEditText().setText(_name);
        contact.getEditText().setText(_contact);
        address.setText(_address);
        email.getEditText().setText(_email);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.updateBtn:
                updatebutton();
                break;
            case R.id.getlcn:
                Toast.makeText(getApplicationContext(),"Test", Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(Profile.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Profile.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When Permission Granted
                } else {
                    //when permission is not granted
                    //req permission
                    ActivityCompat.requestPermissions(Profile.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                    , Manifest.permission.ACCESS_COARSE_LOCATION}
                            , 100);
                }
        }
    }

}




