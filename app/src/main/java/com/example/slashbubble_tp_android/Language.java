package com.example.slashbubble_tp_android;

public class Language {
    private String name;
    private boolean currentLanguage;

    public Language(String name, boolean currentLanguage)
    {
        this.name = name;
        this.currentLanguage = currentLanguage;
    }

    // region getter setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(boolean currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    // end region

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getName();
    }
}

class LanguageDataUtils {


    public static Language[] getLanguages()  {

        Language fr = new Language(App.getAppResources().getString(R.string.fr_language), false);
        Language en = new Language(App.getAppResources().getString(R.string.en_language), true);

        return new Language[]{en, fr};
    }
}
