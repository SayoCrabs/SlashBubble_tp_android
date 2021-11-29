package com.example.slashbubble_tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
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

        // languages part
        Language[] languages = LanguageDataUtils.getLanguages();
        spinner_languages = findViewById(R.id.spinnerLanguage);

        ArrayAdapter<Language> adapter = new ArrayAdapter<Language>(this,
                android.R.layout.simple_spinner_item,
                languages);

        this.spinner_languages.setAdapter(adapter);

        // When user select a List-Item.
        this.spinner_languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                Language langSelected = (Language) adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Sound part
        layoutSound = findViewById(R.id.layoutSound);
        soundVolume = findViewById(R.id.soundVolume);
        sound = findViewById(R.id.sound);
        sound.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        soundVolume.setVisibility(View.GONE);
                        Log.v(TAG, "unChecked");
                    } else {
                        soundVolume.setVisibility(View.VISIBLE);
                    }
                }
        );

    }

    public void onBack(View v) {
        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menu);
    }


}