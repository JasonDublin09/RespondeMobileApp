package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class welcomecontactintro extends AppCompatActivity {

    float x1, x2, y1, y2;
    Button addcontact_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomecontactintro);

        addcontact_btn = (Button) findViewById(R.id.welcomeaddcontact_btn);

        addcontact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwelcomecontact();
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
                    Intent i = new Intent(welcomecontactintro.this, welcome_profile.class);
                    startActivity(i);
                    //swipe right
                }else if(x1 < x2){
                    Intent i = new Intent(welcomecontactintro.this, welcomecontactintro.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

    public void openwelcomecontact(){
        Intent intent = new Intent(this, welcomeaddcontact.class);
        startActivity(intent);
    }

}