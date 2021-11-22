package com.example.slashbubble_tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity {
    private static final String TAG = "Settings";
    SeekBar soundVolume;
    CheckBox sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sound = (CheckBox) findViewById(R.id.sound);
        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                                         {

                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                 if(isChecked)
                                                 {
                                                     Log.v(TAG, "Checked");
                                                 }

                                             }
                                         }
        );

    }


}