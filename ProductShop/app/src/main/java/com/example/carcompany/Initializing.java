package com.example.carcompany;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Initializing extends AppCompatActivity {

    ProgressBar progressInit;
    ActionBar actionBar;
    int progress = 0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initializing);
        actionBar  = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#273036")));
        actionBar.setTitle("CAR SERVICES");
        intent = new Intent(Initializing.this, MainActivity.class);

        initProgressBar();

    }

    public void initProgressBar(){
        progressInit = (ProgressBar) findViewById(R.id.progressInit);
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                progress+=10;
                progressInit.setProgress(progress);

                if(progress == 100){
                    t.cancel();
                    startActivity(intent);
                }
            }
        };

        t.schedule(tt, 0,100);
    }



}
