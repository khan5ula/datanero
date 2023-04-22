package com.team13.datanero.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Color;

public class MainMenu extends JPanel {
    private MainFrame mainFrame;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        /* Logo */
        JLabel logo = new JLabel(new ImageIcon("src/main/java/com/team13/datanero/images/datanero_logo.png"));
        
        /* Teacher mascot */
        ImageIcon originalMascotIcon = new ImageIcon("src/main/java/com/team13/datanero/images/mascot.png");
        int originalWidth = originalMascotIcon.getIconWidth();
        int originalHeight = originalMascotIcon.getIconHeight();

        // Set the desired scale factor
        double scaleFactor = 0.5;

        // Calculate the new width and height
        int newWidth = (int) (originalWidth * scaleFactor);
        int newHeight = (int) (originalHeight * scaleFactor);

        // Create a new ImageIcon with the scaled image
        Image scaledImage = originalMascotIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledMascotIcon = new ImageIcon(scaledImage);

        // Set the new ImageIcon to the JLabel mascot
        JLabel mascot = new JLabel(scaledMascotIcon);
        
        /* Buttons */
        JButton startButton = new ButtonFactory("Aloita peli", 'L', new Color(6,214,160)).getButton();
        JButton settingsButton = new ButtonFactory("Asetukset", 'L', new Color(38, 84, 124)).getButton();
        JButton highScoresButton = new ButtonFactory("Parhaat tulokset", 'L', new Color(255, 209, 102)).getButton();
        JButton exitButton = new ButtonFactory("Poistu", 'L', new Color(239, 71, 111)).getButton();

        /* Button action handling */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame, null);
        startButton.addActionListener(buttonActions);
        settingsButton.addActionListener(buttonActions);
        highScoresButton.addActionListener(buttonActions);
        exitButton.addActionListener(buttonActions);

        /* Add components to panel */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Top and bottom gap of 10 pixels
        
        gbc.gridy = 0;
        add(logo, gbc);
        
        gbc.gridy = 1;
        add(mascot, gbc);
        
        gbc.gridy = 2;
        add(startButton, gbc);
        
        gbc.gridy = 3;
        add(settingsButton, gbc);
        
        gbc.gridy = 4;
        add(highScoresButton, gbc);
        
        gbc.gridy = 5;
        add(exitButton, gbc);
    }
}