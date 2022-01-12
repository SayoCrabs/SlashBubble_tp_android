package com.example.slashbubble_tp_android.credit;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.slashbubble_tp_android.MainActivity;
import com.squareup.picasso.Picasso;
import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class CreditActivity extends AppCompatActivity {
    private static final String TAG = "CreditActivity";

    ImageView iconView;
    TextView textForecast;
    StringRequest stringRequest;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        iconView = findViewById(R.id.icon);
        textForecast = findViewById(R.id.textMeteo);
        String forecast = App.getAppResources().getString(R.string.your_meteo) + App.prefs.getString("localisation", "Toulouse");
        textForecast.setText(forecast);

        getForecast();
    }

    /**
     * Get weather to display of the defined location,
     * by default this will display the weather of toulouse
     */
    public void getForecast() {
        queue = Volley.newRequestQueue(this);
        String url = "https://www.prevision-meteo.ch/services/json/" + App.prefs.getString("localisation", "Toulouse");

        // Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String icon = jsonObject.getJSONObject("current_condition").getString("icon_big");

                        //display
                        Picasso.get().load(icon).into(iconView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.e(TAG, "That didn't work!"));
        queue.cancelAll(TAG);

        // Set the tag on the request.
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }

    public void onBack(View v) {
        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menu);
    }
}