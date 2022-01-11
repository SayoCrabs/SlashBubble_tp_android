package com.example.slashbubble_tp_android.credit;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.R;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class CreditActivity extends AppCompatActivity {

    private static final String TAG = "CreditActivity";

    ImageView iconView;
    TextView textMeteo;
    LocationManager locationManager;
    StringRequest stringRequest;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        iconView = (ImageView) findViewById(R.id.icon);
        textMeteo = findViewById(R.id.textMeteo);
        String meteo = App.getAppResources().getString(R.string.your_meteo) + App.prefs.getString("localisation", "Toulouse");
        textMeteo.setText(meteo);

        getMeteo();
    }

    public void getMeteo() {
        queue = Volley.newRequestQueue(this);
        String url = "https://www.prevision-meteo.ch/services/json/" + App.prefs.getString("localisation", "Toulouse");

        // Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String icon = jsonObject.getJSONObject("current_condition").getString("icon_big");

                            //display
                            Picasso.get().load(icon).into(iconView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didn't work!");
            }
        });
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
}