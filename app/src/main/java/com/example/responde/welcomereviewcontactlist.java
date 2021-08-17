package com.example.responde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class welcomereviewcontactlist extends AppCompatActivity {
    float x1, x2, y1, y2;
    Button addmorecontact_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomereviewcontactlist);

        addmorecontact_btn = (Button) findViewById(R.id.welcomeaddmorecontact_btn);

        addmorecontact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddcontact();
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
                //swipe right
                if(x1 > x2) {
                    Intent i = new Intent(welcomereviewcontactlist.this, welcomelastpage.class);
                    startActivity(i);
                    //swipe left
                }
                break;
        }
        return false;
    }


    public void openaddcontact(){
        Intent intent = new Intent(this, welcomeaddcontact.class);
        startActivity(intent);
    }

}