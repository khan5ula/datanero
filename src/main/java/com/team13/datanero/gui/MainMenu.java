package com.team13.datanero.gui;

import javax.swing.JPanel;
import javax.swing.border.Border;

import com.team13.datanero.gui.Theme.FontStyle;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;

public class MainMenu extends JPanel {
    private MainFrame mainFrame;
    private Theme theme;
    private JLabel logo;
    private JLabel subtitle;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        this.theme = Theme.getInstance();
        setBackground(theme.getScreenBackGroundColor());

        /* Set an empty border with with a padding around the panel */
        Border borderPadding = BorderFactory.createEmptyBorder(110, 100, 100, 60);
        setBorder(borderPadding);

        /* Logo */
        this.logo = new JLabel("DataNero");
        this.logo.setFont(theme.getCustomFont(FontStyle.BOLD, 148));

        /* Sub-title */
        this.subtitle = new JLabel("Tietojenkäsittelyn tietovisa");
        this.subtitle.setFont(theme.getCustomFont(FontStyle.SEMIBOLD, 48));
        
        /* Teacher mascot */
        ImageIcon mascotImage = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_happy.png");
        JLabel mascot = new JLabel(mascotImage);
        
        /* Buttons */
        JButton startButton = new CustomButton("Aloita peli", new Color(6,214,160), 38, FontStyle.BOLD);
        startButton.setPreferredSize(new Dimension(500, 120));
        JButton settingsButton = new CustomButton("Asetukset", new Color(38, 84, 124), 38, FontStyle.BOLD);
        settingsButton.setPreferredSize(new Dimension(500, 120));
        JButton highScoresButton = new CustomButton("Parhaat tulokset", new Color(255, 209, 102), 38, FontStyle.BOLD);
        highScoresButton.setPreferredSize(new Dimension(500, 120));
        JButton exitButton = new CustomButton("Poistu", new Color(239, 71, 111), 38, FontStyle.BOLD);
        exitButton.setPreferredSize(new Dimension(500, 120));

        /* Button action handling */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        startButton.addActionListener(buttonActions);
        settingsButton.addActionListener(buttonActions);
        highScoresButton.addActionListener(buttonActions);
        exitButton.addActionListener(buttonActions);

        /* Add components to panel */
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(logo, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        add(subtitle, gbc);
        
        gbc.gridy = 2;
        add(mascot, gbc);
        
        gbc.gridy = 3;
        add(startButton, gbc);
        
        gbc.gridy = 4;
        add(settingsButton, gbc);
        
        gbc.gridy = 5;
        add(highScoresButton, gbc);
        
        gbc.gridy = 6;
        add(exitButton, gbc);
    }

    public void setTheme() {
        setBackground(theme.getScreenBackGroundColor());
        this.logo.setForeground(theme.getGeneralTextColor());
        this.subtitle.setForeground(theme.getGeneralTextColor());
    }
}