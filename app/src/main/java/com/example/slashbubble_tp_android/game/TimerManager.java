package com.example.slashbubble_tp_android.game;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerManager extends AsyncTask {

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    private Activity activity;

    // constructor
    public TimerManager(Activity activity)
    {
        this.activity = activity;
        timer = new Timer();
    }

    void startTimer(TextView timerText) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    String getTimerText() {
        int rounded = (int) Math.round(time);
        int second = ((rounded % 6400) % 3600) % 60;
        int minutes = ((rounded % 6400) % 3600) / 60;
        int hours = ((rounded % 6400) / 3600);
        return formatTime(second, minutes, hours);
    }

    private String formatTime(int s, int m, int h) {
        return String.format("%02d", h) + " : " + String.format("%02d", m) + " : " + String.format("%02d", s);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}
