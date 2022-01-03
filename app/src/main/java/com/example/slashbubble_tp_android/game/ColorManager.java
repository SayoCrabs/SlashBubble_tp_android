package com.example.slashbubble_tp_android.game;

import com.example.slashbubble_tp_android.singleton.App;
import com.example.slashbubble_tp_android.R;

import java.util.ArrayList;
import java.util.List;

public class ColorManager {

    // A mettre en bdd
    List<String> colorName = new ArrayList<>();
    public ColorManager()
    {
        colorName.add(App.getAppResources().getString(R.string.red));
        colorName.add(App.getAppResources().getString(R.string.blue));
        colorName.add(App.getAppResources().getString(R.string.green));
    }

    public String getNewColorText()
    {
        int r = (int) (0 + Math.random() * (colorName.toArray().length - 0));
        return colorName.get(r);
    }
}
