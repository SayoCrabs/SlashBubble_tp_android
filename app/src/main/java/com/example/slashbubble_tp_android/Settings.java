package com.example.slashbubble_tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity {
    private static final String TAG = "Settings";
    LinearLayout layoutSound;
    SeekBar soundVolume;
    CheckBox sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        layoutSound = findViewById(R.id.layoutSound);
        soundVolume = findViewById(R.id.soundVolume);
        sound = findViewById(R.id.sound);
        sound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {

                Log.v(TAG, "unChecked");
            }

        }
        );

    }


}