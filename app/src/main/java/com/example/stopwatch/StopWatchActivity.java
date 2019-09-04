package com.example.stopwatch;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.util.Locale;

public class StopWatchActivity extends AppCompatActivity {

    private int second = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        if(savedInstanceState != null){
            second = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasrunning");}

        runTimer();
    }


    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", second);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasrunning", wasRunning);
    }


   // @Override
    //protected void onStop(){
      //  super.onStop();
      //  wasRunning = running;
       // running = false;
    //}

    //@Override
    //protected void onStart(){
    //    super.onStart();
     //   if(wasRunning) running = true;
   // }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunning) running = true;
    }



    public void onClickStart(View view){
        running = true;

    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        second = 0;
    }

    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int minutes = (second%3600)/60;
                int secs = second%60;
                String time = String.format(Locale.getDefault(), "%02d: %02d: %02d", hours, minutes, secs);
                timeView.setText(time);
                if(running) second++;

                handler.postDelayed(this, 1000);

            }
        });







    }
}
