package com.example.slashbubble_tp_android.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slashbubble_tp_android.R;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GameActivity";

    // Layout
    ConstraintLayout layoutPauseEnd;
    ConstraintLayout layoutInterfaceGame;

    // Class declaration
    ColorManager colorManager;
    TimerManager timerManager;

    // Text view
    TextView timerText;
    TextView score; int scoreNb;
    TextView colorText;

    // Image View
    ImageButton pauseButton;

    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    List<ImageView> containerImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Layout
        layoutPauseEnd = findViewById(R.id.layoutPauseEnd);
        layoutInterfaceGame = findViewById(R.id.layoutInterfaceGame);

        // Class instantiate
        colorManager = new ColorManager();
        timerManager = new TimerManager(this);

        // Button
        pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(this);

        // Text View
        timerText = findViewById(R.id.timerText);
        score = findViewById(R.id.score);
        colorText = findViewById(R.id.colorText);

        // Image View
        firstImage = findViewById(R.id.firstImage);
        secondImage = findViewById(R.id.secondImage);
        thirdImage = findViewById(R.id.thirdImage);

        // add all image view on List
        containerImage.add(firstImage);
        containerImage.add(secondImage);
        containerImage.add(thirdImage);

        // add the onClickListener on all imageView in the List
        for(ImageView img : containerImage)
        {
            img.setOnClickListener(this);
        }

        // start timers
        timerManager.startTimer(timerText);

        // set the first color when the timer is started
        changeColor();

    }

    /**
     * Here we stop the timer, hide the game interface and display a pause interface
    **/
    @Override
    protected void onPause() {
        super.onPause();
        timerManager.timerTask.cancel();
        layoutInterfaceGame.setVisibility(View.GONE);
        layoutPauseEnd.setVisibility(View.VISIBLE);
    }

    /**
     * When the player return on the app or close the pause menu
     * We "re"start the timer, hide pause interface and display the game
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        timerManager.startTimer(timerText);
        layoutPauseEnd.setVisibility(View.GONE);
        layoutInterfaceGame.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View vi)
    {
        if (vi.getTransitionName() == colorText.getText())
        {
            scoreNb++;
            score.setText("" + scoreNb);
            changeColor();
        }
        else if (vi.getId() == R.id.pauseButton)
        {
            onPause();
        }
        else
        {
           gameOver();
        }
    }

    public void changeColor()
    {
        String newColor = colorManager.getNewColorText();
        colorText.setText(newColor);
        switch (newColor)
        {
            case "Red":
            {
                colorText.setTextColor(getResources().getColor(R.color.red));
                break;
            }
            case "Blue":
            {
                colorText.setTextColor(getResources().getColor(R.color.blue));
                break;
            }
            case "Green":
            {
                colorText.setTextColor(getResources().getColor(R.color.green));
                break;
            }
        }

    }

    /**
     * When the player loose display we change the value and interaction of the pause interface
     * and display it.
     * And we save the result of the player.
     */
    public void gameOver()
    {
        TextView textEnd = (TextView) findViewById(R.id.textPauseEnd);
        textEnd.setText(R.string.gameover);

        Button buttonEnd = (Button) findViewById(R.id.resume);
        buttonEnd.setText(R.string.quit);
        buttonEnd.setOnClickListener(view -> onDestroy());

        layoutInterfaceGame.setVisibility(View.GONE);
        layoutPauseEnd.setVisibility(View.VISIBLE);

        // save the data
    }

    public void resume(View v)
    {
        onRestart();
    }


}