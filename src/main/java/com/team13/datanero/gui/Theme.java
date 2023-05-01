package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Theme {
    private static Theme instance;

    public enum ThemeType {
        LIGHT,
        DARK
    }

    public enum FontStyle {
        BOLD,
        LIGHT,
        MEDIUM,
        REGULAR,
        RETINA,
        SEMIBOLD
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

    /**
     * Getter for current theme.
     * 
     * @return Class variable currentTheme.
     */
    public ThemeType getCurrentTheme() {
        return this.currentTheme;
    }

    /**
     * Method that can be used to change the current theme.
     * 
     * @param theme LIGHT or DARK.
     */
    public void setCurrentTheme(ThemeType theme) {
        this.currentTheme = theme;
    }

    /**
     * Defines the color for general buttons depending on the style.
     * 
     * @return Color depending on the style.
     */
    public Color getButtonTextColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.WHITE;
        }
        // return Color.BLACK;
        return new Color(43, 16, 50);
    }

    /**
     * Defines the color for general text that has no background, depending on the
     * style.
     * 
     * @return Color depending on the style.
     */
    public Color getGeneralTextColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.BLACK;
        }
        // return Color.WHITE;
        return new Color(0, 160, 230);
    }

    /**
     * Defines the color for screen background depending on the style.
     * 
     * @return Color depending on the style.
     */
    public Color getScreenBackGroundColor() {
        if (currentTheme == Theme.ThemeType.LIGHT) {
            return Color.WHITE;
            // return new Color(220, 248, 251);
        }
        // return Color.DARK_GRAY;
        return new Color(43, 16, 50);
    }

    /**
     * Defines the color for answer buttons depending on the style.
     * 
     * @return Color depending on the style.
     */
    public Color getAnswerButtonColor() {
        if (currentTheme == ThemeType.LIGHT) {
            // return Color.DARK_GRAY;
            return new Color(0, 95, 123);
        }
        // return Color.BLACK;
        return new Color(0, 95, 123);
        // return new Color(84, 68, 81);
    }

    public Color getStartGameButtonColor() {
        if (currentTheme == ThemeType.LIGHT) {
            // return new Color(6, 214, 160);
            return new Color(0, 160, 230);
        }
        // return Color.BLACK;
        return new Color(0, 95, 123);
    }

    public Color getSettingsButtonColor() {
        if (currentTheme == ThemeType.LIGHT) {
            return new Color(1, 76, 140);
        }
        // return Color.BLACK;
        return new Color(0, 95, 123);
    }

    public Color getHighScoreButtonColor() {
        if (currentTheme == ThemeType.LIGHT) {
            // return new Color(255, 209, 102);
            return new Color(0, 95, 123);
        }
        // return Color.BLACK;
        return new Color(0, 95, 123);
    }

    public Color getQuitGameButtonColor() {
        if (currentTheme == ThemeType.LIGHT) {
            // return new Color(239, 71, 111);
            return new Color(247, 145, 197);
        }
        // return Color.BLACK;
        return new Color(0, 95, 123);
    }

    public Color getCorrectAnswerButtonColor() {
        if (currentTheme == ThemeType.LIGHT) {
            return new Color(60, 136, 126);
        }
        return new Color(60, 136, 126);
    }

    public Color getIncorrectAnswerButtonColor() {
        if (currentTheme == ThemeType.LIGHT) {
            return new Color(255, 107, 108);
        }
        return new Color(255, 107, 108);
    }

    /**
     * Method that retrieves a font from <b>Fira Code</b> font family.
     * <p>
     * Example of usage: theme.getCustomFont(FontStyle.LIGHT, 48)
     * 
     * @param style    Desired font style. Available styles are defined as ENUMS in
     *                 Theme class.
     *                 <p>
     *                 <b>Available styles:</b>
     *                 </p>
     *                 <ul>
     *                 <li>BOLD</li>
     *                 <li>LIGHT</li>
     *                 <li>MEDIUM</li>
     *                 <li>REGULAR</li>
     *                 <li>RETINA</li>
     *                 <li>SEMIBOLD</li>
     *                 </ul>
     * @param fontSize Desired font size eg. 32, 48, 56, ...
     * @return Font with the desired style and size.
     */
    public Font getCustomFont(FontStyle style, float fontSize) {
        String filepath;

        switch (style) {
            case BOLD:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Bold.ttf";
                break;
            case LIGHT:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Light.ttf";
                break;
            case MEDIUM:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Medium.ttf";
                break;
            case REGULAR:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Retina.ttf";
                break;
            case RETINA:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Retina.ttf";
                break;
            case SEMIBOLD:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-SemiBold.ttf";
                break;
            default:
                filepath = "src/main/java/com/team13/datanero/fonts/FiraCode-Retina.ttf";
        }

        File fontFile = new File(filepath);
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException e) {
            System.out.println("Error: Problem with custom font format: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: IOException occurred with custom font: " + e.getMessage());
            e.printStackTrace();
        }

        if (font != null) {
            font = font.deriveFont(fontSize);
        }
        return font;
    }
}
