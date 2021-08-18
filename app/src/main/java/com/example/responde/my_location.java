package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class my_location extends AppCompatActivity {
private Button cancel_btn;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        cancel_btn = (Button) findViewById(R.id.cancel_btn);
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomepage();
            }
        });

    }

    public void openHomepage(){
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }


    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void displayToastMsg(View v) {
        toastMsg("Report Sent to the Fire District V");
    }

}