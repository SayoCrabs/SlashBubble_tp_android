package com.example.slashbubble_tp_android.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slashbubble_tp_android.App;
import com.example.slashbubble_tp_android.CreditActivity;
import com.example.slashbubble_tp_android.R;
import com.example.slashbubble_tp_android.ShopActivity;
import com.example.slashbubble_tp_android.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GameActivity";

    TextView timerText;
    TextView score; int scoreNb;
    TextView colorText;

    ImageButton pauseButton;

    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    List<ImageView> containerImage = new ArrayList<>();

    // if timer not running stop or not start bubble spawn
    boolean timerRunning = true;

    // timer attributes
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    ColorManager colorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        colorManager = new ColorManager();

        pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(this);

        timerText = findViewById(R.id.timerText);
        score = findViewById(R.id.score);
        colorText = findViewById(R.id.colorText);

        firstImage = findViewById(R.id.firstImage);
        secondImage = findViewById(R.id.secondImage);
        thirdImage = findViewById(R.id.thirdImage);

        // add on List of image view
        containerImage.add(firstImage);
        containerImage.add(secondImage);
        containerImage.add(thirdImage);

        // add the onClickListener on all imageView
        for(ImageView img : containerImage)
        {
            img.setOnClickListener(this);
        }

        timer = new Timer();
        startTimer();

    }

    @Override
    protected void onPause() {
        super.onPause();
        timerRunning = false;
        timerTask.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        timerRunning = true;
        startTimer();
    }

    void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        // set the first color when the timer is started
        colorText.setText(colorManager.changeColorName());
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
    public void onClick(View vi)
    {
        if (vi.getTransitionName() == colorText.getText())
        {
            scoreNb++;
            score.setText("" + scoreNb);
            colorText.setText(colorManager.changeColorName());
        }
        else if (vi.getId() == R.id.pauseButton)
        {
            onPause();
        }
        else
        {
            //gameOver
        }
    }


}