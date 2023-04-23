package com.team13.datanero.gui;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;

public class MainMenu extends JPanel {
    private MainFrame mainFrame;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        /* Set an empty border with with a padding around the panel */
        Border borderPadding = BorderFactory.createEmptyBorder(160, 100, 100, 100);
        setBorder(borderPadding);

        /* Logo */
        JLabel logo = new JLabel(new ImageIcon("src/main/java/com/team13/datanero/images/datanero_logo.png"));
        
        /* Teacher mascot */
        ImageIcon mascotImage = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_happy.png");
        // Set the new ImageIcon to the JLabel mascot
        JLabel mascot = new JLabel(mascotImage);
        
        /* Buttons */
        JButton startButton = new CustomButton("Aloita peli", new Color(6,214,160));
        JButton settingsButton = new CustomButton("Asetukset", new Color(38, 84, 124));
        JButton highScoresButton = new CustomButton("Parhaat tulokset", new Color(255, 209, 102));
        JButton exitButton = new CustomButton("Poistu", new Color(239, 71, 111));

        /* Button action handling */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        startButton.addActionListener(buttonActions);
        settingsButton.addActionListener(buttonActions);
        highScoresButton.addActionListener(buttonActions);
        exitButton.addActionListener(buttonActions);

        /* Add components to panel */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Top and bottom gap of 10 pixels
        
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
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