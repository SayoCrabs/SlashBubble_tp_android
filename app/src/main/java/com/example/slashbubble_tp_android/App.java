package com.example.slashbubble_tp_android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class App extends Application {
    private static Resources resources;
    public static SharedPreferences prefs ;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = this.getSharedPreferences(
                "com.activity_main.app", Context.MODE_PRIVATE);

        resources = getResources();

    }

    public static Resources getAppResources() {
        return resources;
    }

    public static SharedPreferences getPrefs() {
        if (prefs == null)
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("userName", "");
            editor.putInt("bestScore", 0);
            editor.putString("bestTime", "");
            editor.commit();
        }
        return prefs;
    }
}
