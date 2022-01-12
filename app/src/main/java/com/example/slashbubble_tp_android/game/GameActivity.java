package com.example.slashbubble_tp_android.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.slashbubble_tp_android.controller.SaveManager;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GameActivity";

    // region Attributes

    // Layout
    ConstraintLayout layoutPause;
    ConstraintLayout layoutGameOver;
    ConstraintLayout layoutInterfaceGame;

    // Class declaration
    ColorTextManager colorTextManager;
    TimerManager timerManager;
    SaveManager saveManager;

    // Text view
    TextView timerText;
    TextView score; int scoreNb;
    TextView colorText;

    ImageButton pauseButton;

    // Image View
    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    List<ImageView> containerImage = new ArrayList<>();

    // end region

    // region lifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Layout
        layoutGameOver = findViewById(R.id.layoutGameOver);
        layoutPause = findViewById(R.id.layoutPause);
        layoutInterfaceGame = findViewById(R.id.layoutInterfaceGame);

        // Class instantiate
        colorTextManager = new ColorTextManager();
        timerManager = new TimerManager(this);
        saveManager = new SaveManager();

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

        // initialize the bubble choose on the shop
        confImage();

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
        layoutPause.setVisibility(View.VISIBLE);
    }

    /**
     * When the player return on the app or close the pause menu
     * We "re"start the timer, hide pause interface and display the game
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        timerManager.startTimer(timerText);
        layoutPause.setVisibility(View.GONE);
        layoutInterfaceGame.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * When the player loose display we change the value and interaction of the pause interface
     * and display it.
     * And we save the result of the player on the firebase.
     * And the best score of the player on the preference.
     */
    public void gameOver()
    {
        TextView textUserName = findViewById(R.id.textUserNameBestScore);
        textUserName.setText(App.prefs.getString("userName", null));

        TextView textBestScore = findViewById(R.id.textBestScore);
        textBestScore.setText("" + App.prefs.getInt("bestSore", 0));

        TextView textTime = findViewById(R.id.textBestTime);
        textTime.setText(App.prefs.getString("bestTime", null));

        // save the local data
        if(scoreNb >= App.getPrefs().getInt("bestScore", 0))
        {
            SharedPreferences.Editor editor = App.getPrefs().edit();
            editor.putInt("bestScore", scoreNb);
            editor.putString("bestTime", timerText.getText().toString());
            editor.commit();
        }

        layoutInterfaceGame.setVisibility(View.GONE);
        layoutGameOver.setVisibility(View.VISIBLE);

        // save the data on firebase
        saveManager.writeNewScore(App.getPrefs().getString("userName", null),
                scoreNb,
                timerManager.getTimerText());
    }

    public void resume(View v)
    {
        onRestart();
    }

    public void quit(View v)
    {
        onDestroy();
    }

    // end region

    // region onClick
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

    //end region

    public void changeColor()
    {
        String newColor = colorTextManager.getNewColorText();
        colorText.setText(newColor);
        switch (newColor)
        {
            case "Red":
            {
                colorText.setTextColor(getResources().getColor(R.color.red));
                break;
            }
            case "Yellow":
            {
                colorText.setTextColor(getResources().getColor(R.color.yellow));
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
            case "Purple":
            {
                colorText.setTextColor(getResources().getColor(R.color.purple_200));
                break;
            }
        }
    }

    public void confImage()
    {
        if (App.getAppResources().getString(R.string.stone_package).equals(App.prefs.getString("bubblePackage", App.getAppResources().getString(R.string.default_package))))
        {
            firstImage.setBackgroundResource(R.drawable.stone_pink);
            firstImage.setTransitionName(App.getAppResources().getString(R.string.red));
            secondImage.setBackgroundResource(R.drawable.stone_blue);
            secondImage.setTransitionName(App.getAppResources().getString(R.string.blue));
            thirdImage.setBackgroundResource(R.drawable.stone_yellow);
            thirdImage.setTransitionName(App.getAppResources().getString(R.string.yellow));
        }
    }

}

