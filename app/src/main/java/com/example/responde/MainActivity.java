package com.example.responde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout userName, userContact, userEmail;
    Button confirmBtn, gpslocator;
    TextView popupmodal;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView userAddress;
    String date, status;
    SharedPreferences sharedPreferences;
    public String _name,_contact,_address,_email,_contact1,_contact2,_lat,_lng;

    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String CONTACT1="contact1";
    public static final String CONTACT2="contact2";
    public static final String NAME= "name";
    public static final String EMAIL="email";
    public static final String CONTACT= "contact";


    Double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ini fusedLocationProvider
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        Calendar calendar = Calendar.getInstance();
        popupmodal = findViewById(R.id.popupmyotherlocation);
        userName = findViewById(R.id.name);
        userContact = findViewById(R.id.contactNum);
        userAddress = findViewById(R.id.home);
        userEmail = findViewById(R.id.email);
        confirmBtn = findViewById(R.id.confirm);
        gpslocator = findViewById(R.id.gps_locator);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        confirmBtn.setEnabled(false);
        //Firebase confirm button
        confirmBtn.setOnClickListener(this);
        gpslocator.setOnClickListener(this);

        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        date= dateformat.format(calendar.getTime());
        // HashMap<String, String> x =new HashMap<>();
        loadData();
        loadData();
        updateViews();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("IncomingReport");

                    //get all the value
                    String name = userName.getEditText().getText().toString();
                    String contact = userContact.getEditText().getText().toString();
                    String address = userAddress.getText().toString();
                    String email = _email;
                    String option = "OTHER LOCATION";
                    String status = "";
                    String y = "+63"+_contact1;
                    String z = "+63"+_contact2;
                    String[] x = {y, z};
                    List<String> tag = Arrays.asList(x);

                    UserHelperClass helperClass = new UserHelperClass(name, contact, email, address, lat, lng, option, date, status/*, (ArrayList<String>) tag*/, tag);
                    reference.push().setValue(helperClass);
                    Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_SHORT).show();

                    //para sa popup modal
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    //properties of the alertdialog
                    builder.setCancelable(true);
                    builder.setTitle("RESPONDE says: ");
                    builder.setMessage("Report has been sent!");
                    //builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    // @Override
                    // public void onClick(DialogInterface dialogInterface, int i) {
                    //     dialogInterface.cancel();
                    // }
                    // });
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            popupmodal.setVisibility(View.VISIBLE);
                        }
                    });

                    builder.show();

                break;
            case R.id.gps_locator:
                //Check Permissions
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When Permission Granted
                    getCurrentLocation();
                    confirmBtn.setEnabled(true);
                } else {
                    //when permission is not granted
                    //req permission
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                    , Manifest.permission.ACCESS_COARSE_LOCATION}
                            , 100);
                }
        }
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
                            Geocoder geocoder= new Geocoder(MainActivity.this,
                                    Locale.getDefault());
                            //get address
                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(),location.getLongitude(),1
                            );

                            lat=location.getLatitude();
                            lng=location.getLongitude();
                            userAddress.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
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
                                    Geocoder geocoder = new Geocoder(MainActivity.this,
                                            Locale.getDefault());
                                    //get address
                                    List<Address> addresses = geocoder.getFromLocation(
                                            location1.getLatitude(), location1.getLongitude(), 1
                                    );
                                    //set address
                                    lat=location.getLatitude();
                                    lng=location.getLongitude();
                                    userAddress.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
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

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        _contact1= sharedPreferences.getString(CONTACT1,"");
        _contact2= sharedPreferences.getString(CONTACT2,"");
        _name= sharedPreferences.getString(NAME,"");
        _email= sharedPreferences.getString(EMAIL,"");
        _contact= sharedPreferences.getString(CONTACT,"");
    }
    public void updateViews() {
        userName.getEditText().setText(_name);
        userContact.getEditText().setText("+63" + _contact);
        userEmail.getEditText().setText(_email);
    }
}
