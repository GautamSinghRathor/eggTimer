package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int i = 0, j = 0;

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button startButton;
    int sec, min;
    boolean timerIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerSeekBar.setProgress(30);
        timerTextView.setText("0:30");
        startButton.setText("Go");
        timerIsActive = false;
        timerSeekBar.setEnabled(true);
    }

    public void setTimerTextView(int progress){
        String message = "";

        min = progress/60;
        sec = progress%60;
        if(sec == 0){
            message = min + ":00";
        }
        else if(sec < 10){
            message = min + ":0" + sec;
        }else{
            message = min+":"+sec;
        }

        timerTextView.setText(message);
    }

    public void onBtnClick(View view){

        if(timerIsActive == false){

            timerSeekBar.setEnabled(false);
            startButton.setText("stop");
            timerIsActive = true;
             countDownTimer = new CountDownTimer(timerSeekBar.getProgress()*1000 + 100, 1000){
                @Override
                public void onTick(long milliSecondUntilDone) {

                    setTimerTextView((int)milliSecondUntilDone/1000);

                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();

                    MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mp.start();

                    resetTimer();
                }
            }.start();
        }else {
            countDownTimer.cancel();
            resetTimer();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.seekBar);
        timerTextView = (TextView) findViewById(R.id.textView);
        startButton = (Button) findViewById(R.id.button);

        timerSeekBar.setMax(1200);
        timerSeekBar.setProgress(30);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                setTimerTextView(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
