package com.team13.datanero.gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class GameOverScreen extends JDialog {
    private JLabel messageLabel;

    public GameOverScreen(Frame owner, int score) {
        super(owner, "Peli loppui", true);

        messageLabel = new JLabel("Voi rähmä, peli päättyi! Pistesaaliisi on: " + score);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 48));

        /* Add padding to the pop-up screen */
        Border padding = BorderFactory.createEmptyBorder(300, 100, 300, 100);
        messageLabel.setBorder(padding);

        /* Teacher mascot */
        ImageIcon mascotImage = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_terminated.png");
        // Set the new ImageIcon to the JLabel mascot
        JLabel mascot = new JLabel(mascotImage);
        add(mascot);
        
        setLayout(new FlowLayout());
        add(messageLabel);

        pack();
        setLocationRelativeTo(owner);
    }
}
