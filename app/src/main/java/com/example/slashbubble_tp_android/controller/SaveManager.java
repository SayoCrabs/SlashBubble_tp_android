package com.example.slashbubble_tp_android.controller;

import android.content.Context;
import android.util.Log;

import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.model.Scores;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveManager {

    public void writeNewScore(String userName, Number score, String time) {
        Scores scores = new Scores(userName, score, time);

        App.myRef.child("id :").setValue(scores);
    }

    public void writeBestScoreOnTextFile(Context context, Scores data)
    {
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;

        try{
            fOut = context.openFileOutput("bestScore.txt", context.MODE_APPEND);
            osw = new OutputStreamWriter(fOut);
            osw.write(data.toString());
            osw.flush();
        }
        catch (Exception e) {
            Log.v("Error save data :", "cant save data");
        }
        finally {
            try {
                osw.close();
                fOut.close();
            } catch (IOException e) {
                Log.v("Error for close :", "cant close");;
            }
        }
    }
}
