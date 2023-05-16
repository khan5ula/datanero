package com.team13.datanero.backend;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHandler {
    private static LanguageHandler instance;
    private ResourceBundle resourceBundle;
    private Locale currentLocale;

    public LanguageHandler() {
        currentLocale = new Locale("fi");
        resourceBundle = ResourceBundle.getBundle("messages", currentLocale);
    }

    public static LanguageHandler getInstance() {
        if (instance == null) {
            instance = new LanguageHandler();
        }
        return instance;
    }

    public void setLanguage(String language) {
        currentLocale = new Locale(language);
        resourceBundle = ResourceBundle.getBundle("messages", currentLocale);
    }

    public String getCurrentLanguage() {
        return currentLocale.getLanguage();
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
