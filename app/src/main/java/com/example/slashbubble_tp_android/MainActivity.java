package com.example.slashbubble_tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.slashbubble_tp_android.controller.SaveManager;
import com.example.slashbubble_tp_android.credit.CreditActivity;
import com.example.slashbubble_tp_android.model.Scores;
import com.example.slashbubble_tp_android.settings.Language;
import com.example.slashbubble_tp_android.shop.ShopActivity;
import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.game.GameActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";

    // region Attributes

    SaveManager saveOldBestScore;
    Language languageManager;

    Button btnStart;
    Button btnShop;
    Button btnQuit;
    Button btnCredit;
    ImageButton btnModifyUserName;

    TextView userName;
    Context context;

    // end region

    // region lifeCycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize context
        this.context = this;

        saveOldBestScore = new SaveManager();
        languageManager = new Language();

        // initialize button
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnShop = findViewById(R.id.btnShop);
        btnShop.setOnClickListener(this);
        btnCredit = findViewById(R.id.btnCredit);
        btnCredit.setOnClickListener(this);
        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(this);
        btnModifyUserName = findViewById(R.id.btnModifyUserName);
        btnModifyUserName.setOnClickListener(this);

        userName = findViewById(R.id.userName);
        userName.setText(App.getPrefs().getString("userName", null));

        if(userName.getText().toString().isEmpty())
        {
            firstStartingApp();
        }
    }

    // end region

    // region onClick

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
            case R.id.btnModifyUserName:
            {
                firstStartingApp();
                break;
            }
            default:
            {
                //do nothing
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

    // end region

    // region menu

    /**
     * Show a menu when user click on the setting button
     * @param v
     */
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_settings, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.en:
                    languageManager.changeAppLanguage(App.getAppResources().getString(R.string.en_language));
                    return true;
                case R.id.fr:
                    languageManager.changeAppLanguage(App.getAppResources().getString(R.string.fr_language));
                    return true;
                case R.id.localisation:
                    changeLocalisation();
                    return true;
                default:
                    return true;
            }
        });
        popup.show();
    }

    // end region

    // region alert dialog

    /**
     * If this is the first time that the user has launched the application or
     * that he presses the button to modify his name, a dialog box is displayed
     */
    public void firstStartingApp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final EditText userNameEdition = new EditText(context);

        builder.setView(userNameEdition);
        builder.setMessage(App.getAppResources().getString(R.string.set_user_name))
                .setTitle(App.getAppResources().getString(R.string.hello_world))
                .setPositiveButton(App.getAppResources().getString(R.string.valid), (dialog, which) -> {
                    // Save on file the old userName with his best score
                    saveOldBestScore.writeBestScoreOnTextFile(this, new Scores(
                            App.prefs.getString("userName", null),
                            App.prefs.getInt("bestSore", 0),
                            App.prefs.getString("bestTime", null)));

                    // change the userName
                    SharedPreferences.Editor editor = App.getPrefs().edit();
                    editor.putString("userName", userNameEdition.getText().toString());
                    editor.commit();
                    userName.setText(userNameEdition.getText().toString());
                    dialog.dismiss();
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * When the user want to change his location a dialogue box is displayed
     */
    public void changeLocalisation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final EditText localisationEdition = new EditText(context);

        builder.setView(localisationEdition);
        builder.setMessage(App.getAppResources().getString(R.string.alert_change_localisation))
                .setTitle(App.getAppResources().getString(R.string.modification))
                .setPositiveButton(App.getAppResources().getString(R.string.valid), (dialog, which) -> {
                    // change the localisation
                    SharedPreferences.Editor editor = App.getPrefs().edit();
                    editor.putString("localisation", localisationEdition.getText().toString());
                    editor.commit();
                    dialog.dismiss();
                })
                .setNegativeButton(App.getAppResources().getString(R.string.back_button),(dialog, which) -> {
                    dialog.dismiss();
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
    // end region
}