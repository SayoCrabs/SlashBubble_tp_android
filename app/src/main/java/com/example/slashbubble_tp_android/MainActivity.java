package com.example.slashbubble_tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.slashbubble_tp_android.game.GameActivity;
import com.example.slashbubble_tp_android.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";

    // region Attributes

    Button btnStart;
    Button btnShop;
    Button btnQuit;
    Button btnSettings;
    Button btnCredit;
    ImageButton btnModifyUserName;

    TextView userName;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize context
        this.context = this;

        // initialize button
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnShop = findViewById(R.id.btnShop);
        btnShop.setOnClickListener(this);
        btnCredit = findViewById(R.id.btnCredit);
        btnCredit.setOnClickListener(this);
        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(this);
        btnSettings = findViewById(R.id.btnSetting);
        btnSettings.setOnClickListener(this);

        btnModifyUserName = findViewById(R.id.btnModifyUserName);
        btnModifyUserName.setOnClickListener(this);

        userName = findViewById(R.id.userName);
        userName.setText(App.getPrefs().getString("userName", null));

        if(userName.getText().toString().isEmpty())
        {
            firstStartingApp();
        }
    }

    @Override
    public void onClick(View vi) {
        switch (vi.getId())
        {
            case R.id.btnStart:
            {
                Intent game = new Intent(context, GameActivity.class);
                startActivity(game);
                break;
            }
            case R.id.btnShop:
            {
                Intent shop = new Intent(context, ShopActivity.class);
                startActivity(shop);
                break;
            }
            case R.id.btnCredit:
            {
                Intent credit = new Intent(context, CreditActivity.class);
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
                Intent settings = new Intent(context, SettingsActivity.class);
                startActivity(settings);
                break;
            }
            case R.id.btnModifyUserName:
            {
                firstStartingApp();
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

        builder.setMessage(App.getAppResources().getString(R.string.alert_quit_message))
                .setTitle(App.getAppResources().getString(R.string.warning))
                .setPositiveButton(App.getAppResources().getString(R.string.quit), (dialog, which) -> {
                    System.exit(0);
                    dialog.dismiss();
                })
                .setNegativeButton(App.getAppResources().getString(R.string.back_button),(dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void firstStartingApp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final EditText userNameEdition = new EditText(context);

        builder.setView(userNameEdition);
        builder.setMessage(App.getAppResources().getString(R.string.set_user_name))
                .setTitle(App.getAppResources().getString(R.string.warning))
                .setPositiveButton(App.getAppResources().getString(R.string.valid), (dialog, which) -> {
                    SharedPreferences.Editor editor = App.getPrefs().edit();
                    editor.putString("userName", userNameEdition.getText().toString());
                    editor.commit();
                    dialog.dismiss();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}