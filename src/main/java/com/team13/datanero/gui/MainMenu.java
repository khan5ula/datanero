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
    private JButton startButton;
    private JButton settingsButton;
    private JButton highScoresButton;
    private JButton exitButton;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        this.theme = Theme.getInstance();
        setBackground(theme.getScreenBackGroundColor());

        /* Set an empty border with with a padding around the panel */
        Border borderPadding = BorderFactory.createEmptyBorder(110, 100, 100, 60);
        setBorder(borderPadding);

        /* Logo */
        // this.logo = new JLabel("DataNero");
        // this.logo.setFont(theme.getCustomFont(FontStyle.BOLD, 148));
        ImageIcon logoImage = new ImageIcon("src/main/java/com/team13/datanero/images/datanerologo.png");
        JLabel logoLabel = new JLabel(logoImage);

        /* Sub-title */
        this.subtitle = new JLabel("Tietojenkäsittelyn tietovisa");
        this.subtitle.setFont(theme.getCustomFont(FontStyle.SEMIBOLD, 48));

        /* Teacher mascot */
        ImageIcon mascotImage = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_happy.png");
        JLabel mascot = new JLabel(mascotImage);

        /* Create buttons */
        this.startButton = new CustomButton("Aloita peli", theme.getStartGameButtonColor(), 38, FontStyle.BOLD);
        this.startButton.setPreferredSize(new Dimension(500, 120));

        this.settingsButton = new CustomButton("Asetukset", theme.getSettingsButtonColor(), 38, FontStyle.BOLD);
        this.settingsButton.setPreferredSize(new Dimension(500, 120));

        this.highScoresButton = new CustomButton("Parhaat tulokset", new Color(255, 209, 102), 38, FontStyle.BOLD);
        this.highScoresButton.setPreferredSize(new Dimension(500, 120));

        this.exitButton = new CustomButton("Poistu", new Color(239, 71, 111), 38, FontStyle.BOLD);
        this.exitButton.setPreferredSize(new Dimension(500, 120));

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
        add(logoLabel, gbc);

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
        // this.logo.setForeground(theme.getGeneralTextColor());
        this.subtitle.setForeground(theme.getGeneralTextColor());
        this.startButton.setBackground(theme.getStartGameButtonColor());
        this.settingsButton.setBackground(theme.getSettingsButtonColor());
        this.highScoresButton.setBackground(theme.getHighScoreButtonColor());
        this.exitButton.setBackground(theme.getQuitGameButtonColor());
    }
}