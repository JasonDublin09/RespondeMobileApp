package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class welcomepage extends AppCompatActivity {


    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

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
                if(x1> x2){
                Intent i = new Intent(welcomepage.this, welcome_profile.class);
                startActivity(i);
                //swipe right
            }else if(x1 > x2){
                Intent i = new Intent(welcomepage.this, welcome_profile.class);
                startActivity(i);
            }
            break;
        }
        return false;
    }

}