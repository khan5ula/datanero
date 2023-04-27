package com.team13.datanero.gui;

import java.awt.Color;

public class Theme {
    private static Theme instance;

    public enum ThemeType {
        LIGHT,
        DARK
    }

    private ThemeType currentTheme;

    private Theme() {
    }

    public static Theme getInstance() {
        if (instance == null) {
            instance = new Theme();
            instance.setCurrentTheme(ThemeType.LIGHT);
        }
        return instance;
    }

    public ThemeType getCurrentTheme() {
        return this.currentTheme;
    }

    public void setCurrentTheme(ThemeType theme) {
        this.currentTheme = theme;
    }

    public Color getButtonTextColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    public Color getQuestionTextColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    public Color getScreenBackGroundColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.WHITE;
        } else {
            return Color.DARK_GRAY;
        }
    }
}
