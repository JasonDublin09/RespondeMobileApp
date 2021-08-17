package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class welcome_profile extends AppCompatActivity {

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_profile);



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
