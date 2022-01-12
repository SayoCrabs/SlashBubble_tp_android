package com.example.slashbubble_tp_android.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.slashbubble_tp_android.MainActivity;
import com.example.slashbubble_tp_android.R;
import com.example.slashbubble_tp_android.singleton.App;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ArrayList<Bubble> items = new ArrayList<>();
        items.add(new Bubble(App.getAppResources().getString(R.string.default_package), R.drawable.round_violet, R.drawable.round_blue, R.drawable.round_green));
        items.add(new Bubble(App.getAppResources().getString(R.string.stone_package), R.drawable.stone_pink, R.drawable.stone_blue,R.drawable.stone_yellow));

        ListViewAdapter adapter = new ListViewAdapter(this, items);
        ListView listView = findViewById(R.id.listViewImage);

        listView.setAdapter(adapter);
    }

    public void onBack(View v) {
        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menu);
    }
}