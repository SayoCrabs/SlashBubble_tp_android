package com.example.slashbubble_tp_android.game;

import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.R;

import java.util.ArrayList;
import java.util.List;

public class ColorTextManager {

    List<String> colorName = new ArrayList<>();
    String stone = App.getAppResources().getString(R.string.stone_package);
    public ColorTextManager()
    {
        if (stone.equals(App.prefs.getString("bubblePackage", App.getAppResources().getString(R.string.default_package))))
        {
            colorName.add(App.getAppResources().getString(R.string.red));
            colorName.add(App.getAppResources().getString(R.string.blue));
            colorName.add(App.getAppResources().getString(R.string.yellow));
        } else {
            colorName.add(App.getAppResources().getString(R.string.purple));
            colorName.add(App.getAppResources().getString(R.string.blue));
            colorName.add(App.getAppResources().getString(R.string.green));
        }

    }

    public String getNewColorText()
    {
        int r = (int) (0 + Math.random() * (colorName.toArray().length - 0));
        return colorName.get(r);
    }
}
