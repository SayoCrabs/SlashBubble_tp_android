package com.example.slashbubble_tp_android.singleton;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import com.example.slashbubble_tp_android.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class App extends Application {

    private static Resources resources;

    // singleton
    public static SharedPreferences prefs;
    public static FirebaseDatabase database;
    public static DatabaseReference myRef;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = this.getSharedPreferences(
                "com.activity_main.app", Context.MODE_PRIVATE);

        resources = getResources();
        //getTheDatabase();

    }

    public static Resources getAppResources() {
        return resources;
    }

    public static SharedPreferences getPrefs() {
        if (prefs == null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("userName", "");
            editor.putInt("bestScore", 0);
            editor.putString("bestTime", "");
            editor.putString("language", App.getAppResources().getString(R.string.en_language));
            editor.putString("localisation", "Toulouse");
            editor.putString("bubblePackage", App.getAppResources().getString(R.string.default_package));
            editor.commit();
        }
        return prefs;
    }

    public static void getTheDatabase()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.w("onDataChange", "value changed");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("APPX", "Failed to read value.", error.toException());
            }
        });
    }
}
