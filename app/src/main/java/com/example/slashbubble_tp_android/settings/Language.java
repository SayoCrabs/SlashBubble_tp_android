package com.example.slashbubble_tp_android.settings;

import android.content.SharedPreferences;

import com.example.slashbubble_tp_android.singleton.App;

public class Language {

    public Language(){}

    public void changeAppLanguage(String languageSelected)
    {
        SharedPreferences.Editor editor = App.getPrefs().edit();
        editor.putString("language", languageSelected);
        editor.commit();
    }
}
