package com.example.slashbubble_tp_android.model;

public class Scores {
    public String username;
    public Number score;
    public String time;

    public Scores() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Scores(String username, Number score, String time) {
        this.username = username;
        this.score = score;
        this.time = time;
    }
}
