package com.example.slashbubble_tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";

    // region Buttons

    Button btnStart;
    Button btnShop;
    Button btnQuit;
    Button btnSettings;
    Button btnCredit;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize context
        this.context = this;

        // initialize button
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnShop = (Button) findViewById(R.id.btnShop);
        btnShop.setOnClickListener(this);
        btnCredit = (Button) findViewById(R.id.btnCredit);
        btnCredit.setOnClickListener(this);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(this);
        btnSettings = (Button) findViewById(R.id.btnSetting);
        btnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View vi) {
        switch (vi.getId())
        {
            case R.id.btnStart:
            {
                Intent game = new Intent(context, Game.class);
                startActivity(game);
                break;
            }
            case R.id.btnShop:
            {
                Intent shop = new Intent(context, Shop.class);
                startActivity(shop);
                break;
            }
            case R.id.btnCredit:
            {
                Intent credit = new Intent(context, Credit.class);
                startActivity(credit);
                break;
            }
            case R.id.btnQuit:
            {
                onBackPressed();
                break;
            }
            case R.id.btnSetting:
            {
                Intent settings = new Intent(context, Settings.class);
                startActivity(settings);
                break;
            }
            default:
            {
                Log.v(TAG, "ERREUR");
                break;
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Voulez vous vraiment quitter l'application ?")
                .setTitle("Attention !")
                .setPositiveButton("Continuer", (dialog, which) -> {
                    System.exit(0);
                    dialog.dismiss();
                })
                .setNegativeButton("Annuler",(dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}