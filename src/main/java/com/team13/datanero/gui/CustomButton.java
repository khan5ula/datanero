package com.team13.datanero.gui;

import java.awt.Color;

import javax.swing.JButton;

import com.team13.datanero.gui.Theme.FontStyle;

public class CustomButton extends JButton {
    private Theme theme;

    public CustomButton(String name, Color color, int fontSize, FontStyle style) {
        super(name);
        this.setBackground(color);
        this.setFocusPainted(false);
        this.theme = Theme.getInstance();
        this.setFont(theme.getCustomFont(style, fontSize));
        this.setForeground(Color.WHITE);
    }
}
