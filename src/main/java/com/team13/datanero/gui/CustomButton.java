package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CustomButton extends JButton {

    public CustomButton(String name, Color color) {
        super(name);
        this.setBackground(color);

        File font_file = new File("src/main/java/com/team13/datanero/fonts/FiraCode-Medium.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);

            // Set the custom font and size
            Font customFont = font.deriveFont(Font.BOLD, 38);

            // Set the font, text color and background color
            this.setFont(customFont);
            this.setForeground(Color.WHITE);

            /* Enable painting with background color */
            this.setOpaque(true);

            /* Remove focus border */
            this.setFocusPainted(false);
        } catch (FontFormatException e) {
            System.out.println("Error: Problem with custom font format: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: IOException occured with custom font: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
