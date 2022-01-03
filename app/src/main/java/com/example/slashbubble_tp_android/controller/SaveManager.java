package com.example.slashbubble_tp_android.controller;

import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.model.Scores;

public class SaveManager {


    public void writeNewScore(String userName, Number score, String time) {
        Scores scores = new Scores(userName, score, time);

        App.myRef.child("id :").setValue(scores);
    }
}
