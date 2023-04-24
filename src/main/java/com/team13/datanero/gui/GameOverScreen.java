package com.team13.datanero.gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class GameOverScreen extends JDialog {
    private JLabel messageLabel;

    public GameOverScreen(Frame owner, int score) {
        super(owner, "Peli loppui", true);

        /* Load the custom font from file */
        File font_file = new File("src/main/java/com/team13/datanero/fonts/FiraCode-Light.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Create backup font in case the custom one could not be loaded */
        Font backupFont = new Font("Arial", Font.BOLD, 48);

        /* Derive custom font with desired style if the font is not null */
        Font customFont = font != null ? font.deriveFont(Font.PLAIN, 48) : null;

        messageLabel = new JLabel("Voi rähmä, peli päättyi! Pistesaaliisi on: " + score);
        messageLabel.setFont(font != null ? customFont : backupFont);

        /* Add padding to the pop-up screen */
        Border padding = BorderFactory.createEmptyBorder(300, 100, 300, 100);
        messageLabel.setBorder(padding);

        /* Teacher mascot */
        ImageIcon mascotImage = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_terminated.png");
        JLabel mascot = new JLabel(mascotImage);
        add(mascot);

        setLayout(new FlowLayout());
        add(messageLabel);

        pack();
        setLocationRelativeTo(owner);
    }
}