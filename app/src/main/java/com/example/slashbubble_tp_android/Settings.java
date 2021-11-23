package com.example.slashbubble_tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {
    private static final String TAG = "Settings";

    LinearLayout layoutSound;

    SeekBar soundVolume;
    CheckBox sound;
    AudioManager audioManager;

    Spinner spinner_languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Language[] languages = LanguageDataUtils.getLanguages();

        spinner_languages = findViewById(R.id.spinnerLanguage);

        ArrayAdapter<Language> adapter = new ArrayAdapter<Language>(this,
                android.R.layout.simple_spinner_item,
                languages);

        this.spinner_languages.setAdapter(adapter);

        layoutSound = findViewById(R.id.layoutSound);
        soundVolume = findViewById(R.id.soundVolume);
        sound = findViewById(R.id.sound);
        sound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                soundVolume.setVisibility(View.GONE);
                Log.v(TAG, "unChecked");
            }
            else
            {
                soundVolume.setVisibility(View.VISIBLE);
            }
        }
        );

    }


}