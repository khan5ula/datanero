package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ButtonFactory {
    JButton button;

    public ButtonFactory(String name, char size, Color color) {
        int width = 0;
        int height = 0;

        switch (size) {
            case 's': case 'S':
                width = 100;
                height = 50;
                break;
            case 'm': case 'M':
                width = 150;
                height = 100;
                break;
            case 'l': case 'L':
                width = 800;
                height = 130;
                break;
            default:
                width = 800;
                height = 130;
        }

        this.button = new JButton(name);
        this.button.setBackground(color);
        this.button.setPreferredSize(new Dimension(width, height));

        /* Set the font, text color and background color */
        this.button.setFont(new Font("Arial", Font.BOLD, 38));
        this.button.setForeground(Color.WHITE);

        /* Add a border with rounded corners */
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 117, 210), 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        /* Set the button size */
        button.setPreferredSize(new Dimension(800, 130));

        /* Enable painting with background color */
        button.setOpaque(true);

        /* Remove focus border */
        button.setFocusPainted(false);
    }

    public JButton getButton() {
        return this.button;
    }
    
}
