package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class SettingsScreen extends JPanel {
    private MainFrame mainFrame;
    private JButton soundButton;
    private JButton languageButton;
    private JButton difficultyButton;
    private JButton themeButton;
    private ArrayList<JButton> optionButtons;

    public SettingsScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.optionButtons = new ArrayList<JButton>();
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets padding = new Insets(10, 10, 10, 10);

        /* Add padding to the screen */
        Border borderPadding = BorderFactory.createEmptyBorder(300, 100, 0, 100);
        setBorder(borderPadding);

        /* Load the custom font from file */
        File font_file = new File("src/main/java/com/team13/datanero/fonts/FiraCode-Bold.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Create Header label */
        JLabel header = new JLabel("Asetukset");
        Font customFont = font.deriveFont(Font.PLAIN, 84);
        header.setFont(customFont);

        /* Add Header label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 200, 10);
        add(header, gbc);

        /* Create options */
        String[] optionNames = { "Äänet", "Kieli", "Vaikeustaso", "Teema" };
        GridBagConstraints optionGbc = new GridBagConstraints();
        optionGbc.gridx = 0;
        optionGbc.gridy = 1;
        optionGbc.gridwidth = 1;
        optionGbc.gridheight = 1;
        optionGbc.weightx = 1.0;
        optionGbc.weighty = 0.0;
        optionGbc.anchor = GridBagConstraints.LINE_END;
        optionGbc.insets = padding;

        GridBagConstraints optionButtonGbc = new GridBagConstraints();
        optionButtonGbc.gridx = 1;
        optionButtonGbc.gridy = 1;
        optionButtonGbc.gridwidth = 1;
        optionButtonGbc.gridheight = 1;
        optionButtonGbc.weightx = 1.0;
        optionButtonGbc.weighty = 0.0;
        optionButtonGbc.anchor = GridBagConstraints.LINE_START;
        optionButtonGbc.insets = padding;

        Font customFont32 = font.deriveFont(Font.PLAIN, 32);
        Dimension buttonDimension = new Dimension(250, 60);

        for (int i = 0; i < 4; i++) {
            JLabel optionLabel = new JLabel(optionNames[i]);
            optionLabel.setFont(customFont32);
            add(optionLabel, optionGbc);

            switch (i) {
                case 0: // Set sound button
                    this.soundButton = new CustomButton("Ei saatavilla", Color.WHITE, 24);
                    this.soundButton.setForeground(Color.BLACK);
                    this.soundButton.setPreferredSize(buttonDimension);
                    this.soundButton.setMaximumSize(buttonDimension);
                    this.soundButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    add(this.soundButton, optionButtonGbc);
                    this.optionButtons.add(this.soundButton);
                    break;
                case 1: // Set language button
                    this.languageButton = new CustomButton("Ei saatavilla", Color.WHITE, 24);
                    this.languageButton.setForeground(Color.BLACK);
                    this.languageButton.setPreferredSize(buttonDimension);
                    this.languageButton.setMaximumSize(buttonDimension);
                    this.languageButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    add(this.languageButton, optionButtonGbc);
                    this.optionButtons.add(this.languageButton);
                    break;
                case 2: // Set difficulty button
                    this.difficultyButton = new CustomButton("Ei saatavilla", Color.WHITE, 24);
                    this.difficultyButton.setForeground(Color.BLACK);
                    this.difficultyButton.setPreferredSize(buttonDimension);
                    this.difficultyButton.setMaximumSize(buttonDimension);
                    this.difficultyButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    add(this.difficultyButton, optionButtonGbc);
                    this.optionButtons.add(this.difficultyButton);
                    this.optionButtons.add(this.difficultyButton);
                    break;
                case 3: // Set theme button
                    this.themeButton = new CustomButton("Vaalea", Color.WHITE, 24);
                    this.themeButton.setForeground(Color.BLACK);
                    this.themeButton.setPreferredSize(buttonDimension);
                    this.themeButton.setMaximumSize(buttonDimension);
                    this.themeButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    this.themeButton.addActionListener(new ThemeButtonListener());
                    add(this.themeButton, optionButtonGbc);
                    this.optionButtons.add(this.themeButton);
                    break;
            }
            optionGbc.gridy++;
            optionButtonGbc.gridy++;
        }

        /* Create exit button */
        JButton exitButton = new CustomButton("Palaa päävalikkoon", new Color(239, 71, 111), 32);
        exitButton.setActionCommand("Palaa päävalikkoon");
        exitButton.setPreferredSize(new Dimension(500, 120));
        exitButton.setMaximumSize(new Dimension(500, 120));

        /* Define action for exit button */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        exitButton.addActionListener(buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 0;
        gbc.gridy = optionNames.length + 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = padding;
        add(exitButton, gbc);
    }

    private void changeTheme() {
        // TODO: Implement theme change logic

        /* If player chose dark theme */
        if (themeButton.getText().equals("Vaalea")) {
            for (JButton button : this.optionButtons) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }
            themeButton.setText("Tumma");

        /* If player chose light theme */
        } else {
            for (JButton button : this.optionButtons) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
            themeButton.setText("Vaalea");
        }
    }

    private class ThemeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeTheme();
        }
    }

}