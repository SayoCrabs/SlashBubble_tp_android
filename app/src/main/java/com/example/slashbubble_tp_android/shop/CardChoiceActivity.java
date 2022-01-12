package com.example.slashbubble_tp_android.shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.slashbubble_tp_android.R;
import com.example.slashbubble_tp_android.singleton.App;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class CardChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_choice);
    }
}

class Card
{
    public String namePackage;
    public int imageLeft;
    public int imageCenter;
    public int imageRight;

    Card(String name,int left, int center, int right ) {
        namePackage = name;
        imageLeft = left;
        imageCenter = center;
        imageRight = right;
    }
}

class ListViewAdapter extends ArrayAdapter<Card> {
    int selectedPosition = 0;

    ListViewAdapter(@NonNull Context context, ArrayList<Card> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.activity_card_choice, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Card currentItem = getItem(position);
        TextView name = listItem.findViewById(R.id.namePackageImage);
        name.setText(currentItem.namePackage);

        Boolean isCurrentPackage = currentItem.namePackage.equals(App.prefs.getString("bubblePackage", App.getAppResources().getString(R.string.default_package)));

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch r = (Switch) listItem.findViewById(R.id.sw);
        r.setChecked(isCurrentPackage);
        r.setTag(position);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = (Integer)view.getTag();
                SharedPreferences.Editor editor = App.getPrefs().edit();
                editor.putString("bubblePackage", currentItem.namePackage);
                editor.commit();
                notifyDataSetChanged();
            }
        });

        ImageView left = listItem.findViewById(R.id.leftImage);
        left.setBackgroundResource(currentItem.imageLeft);

        ImageView center = listItem.findViewById(R.id.centerImage);
        center.setBackgroundResource(currentItem.imageCenter);

        ImageView right = listItem.findViewById(R.id.rightImage);
        right.setBackgroundResource(currentItem.imageRight);



        return listItem;
    }

}