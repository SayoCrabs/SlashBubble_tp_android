package com.example.slashbubble_tp_android.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.slashbubble_tp_android.R;
import com.example.slashbubble_tp_android.singleton.App;

import java.util.ArrayList;

class ListViewAdapter extends ArrayAdapter<Bubble> {
    int selectedPosition = 0;

    ListViewAdapter(@NonNull Context context, ArrayList<Bubble> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.activity_bubble_choice, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Bubble currentItem = getItem(position);
        TextView name = listItem.findViewById(R.id.namePackageImage);
        name.setText(currentItem.namePackage);

        boolean isCurrentPackage = currentItem.namePackage.equals(App.prefs.getString("bubblePackage", App.getAppResources().getString(R.string.default_package)));

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch r = (Switch) listItem.findViewById(R.id.sw);
        r.setChecked(isCurrentPackage);
        r.setTag(position);
        r.setOnClickListener(view -> {
            selectedPosition = (Integer)view.getTag();
            SharedPreferences.Editor editor = App.getPrefs().edit();
            editor.putString("bubblePackage", currentItem.namePackage);
            editor.commit();
            notifyDataSetChanged();
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
