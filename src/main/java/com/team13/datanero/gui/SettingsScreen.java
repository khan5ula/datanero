package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.team13.datanero.gui.Theme.FontStyle;
import com.team13.datanero.gui.Theme.ThemeType;

public class SettingsScreen extends JPanel {
    private MainFrame mainFrame;
    private JLabel header;
    private JButton soundButton;
    private JButton languageButton;
    private JButton difficultyButton;
    private JButton themeButton;
    private JButton exitButton;
    private ArrayList<JButton> optionButtons;
    private ArrayList<JLabel> optionTextList;
    private Theme theme;
    private Sound sound;

    public SettingsScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.optionButtons = new ArrayList<JButton>();
        this.optionTextList = new ArrayList<JLabel>();
        this.theme = Theme.getInstance();
        this.sound = new Sound("src/main/java/com/team13/datanero/sounds/backgroundmusicdemo.wav");
        init();
    }

    /**
     * Method that initializes the settings screen elements.
     */
    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets padding = new Insets(10, 10, 10, 10);

        /* Add padding to the screen */
        Border borderPadding = BorderFactory.createEmptyBorder(300, 100, 0, 100);
        setBorder(borderPadding);

        /* Create Header label */
        this.header = new JLabel("Asetukset");
        header.setFont(theme.getCustomFont(FontStyle.BOLD, 84));
        // TODO: Add some border and background to the header.

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

        Dimension buttonDimension = new Dimension(250, 60);

        for (int i = 0; i < 4; i++) {
            this.optionTextList.add(new JLabel(optionNames[i]));
            optionTextList.get(i).setFont(theme.getCustomFont(FontStyle.SEMIBOLD, 32));
            add(optionTextList.get(i), optionGbc);

            switch (i) {
                case 0: // Set sound button
                    this.soundButton = new CustomButton("Pois päältä", Color.WHITE, 24, FontStyle.SEMIBOLD);
                    this.soundButton.setForeground(Color.BLACK);
                    this.soundButton.setPreferredSize(buttonDimension);
                    this.soundButton.setMaximumSize(buttonDimension);
                    this.soundButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    this.soundButton.addActionListener(new SoundButtonListener());
                    add(this.soundButton, optionButtonGbc);
                    this.optionButtons.add(this.soundButton);
                    break;
                case 1: // Set language button
                    this.languageButton = new CustomButton("Ei saatavilla", Color.WHITE, 24, FontStyle.SEMIBOLD);
                    this.languageButton.setForeground(Color.BLACK);
                    this.languageButton.setPreferredSize(buttonDimension);
                    this.languageButton.setMaximumSize(buttonDimension);
                    this.languageButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    add(this.languageButton, optionButtonGbc);
                    this.optionButtons.add(this.languageButton);
                    break;
                case 2: // Set difficulty button
                    this.difficultyButton = new CustomButton("Ei saatavilla", Color.WHITE, 24, FontStyle.SEMIBOLD);
                    this.difficultyButton.setForeground(Color.BLACK);
                    this.difficultyButton.setPreferredSize(buttonDimension);
                    this.difficultyButton.setMaximumSize(buttonDimension);
                    this.difficultyButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    add(this.difficultyButton, optionButtonGbc);
                    this.optionButtons.add(this.difficultyButton);
                    this.optionButtons.add(this.difficultyButton);
                    break;
                case 3: // Set theme button
                    this.themeButton = new CustomButton("Vaalea", Color.WHITE, 24, FontStyle.SEMIBOLD);
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
        this.exitButton = new CustomButton("Palaa päävalikkoon", theme.getQuitGameButtonColor(), 32, FontStyle.BOLD);
        this.exitButton.setActionCommand("Palaa päävalikkoon");
        this.exitButton.setPreferredSize(new Dimension(500, 120));
        this.exitButton.setMaximumSize(new Dimension(500, 120));

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

    private void changeSound() {
        if (soundButton.getText().equals("Pois päältä")) {
            System.out.println("Status: Player switched sounds on");
            playSound(sound);
            soundButton.setText("Päällä");
        } else {
            System.out.println("Status: Player switched sounds off");
            stopSound(sound);
            soundButton.setText("Pois päältä");
        }
    }

    /**
     * Method that changes the system theme between Light and Dark.
     */
    private void changeTheme() {
        /* If player chose dark theme */
        if (themeButton.getText().equals("Vaalea")) {
            System.out.println("Status: Player chose dark theme");
            theme.setCurrentTheme(ThemeType.DARK);
            themeButton.setText("Tumma");

            /* If player chose light theme */
        } else {
            System.out.println("Status: Player chose light theme");
            theme.setCurrentTheme(ThemeType.LIGHT);
            setBackground(theme.getScreenBackGroundColor());
            themeButton.setText("Vaalea");
        }

        /* General theme adjustments */
        setBackground(theme.getScreenBackGroundColor());

        for (JButton button : this.optionButtons) {
            button.setBackground(theme.getButtonTextColor()); // Borrow button text colors for this
            button.setForeground(theme.getGeneralTextColor());
        }

        for (JLabel label : this.optionTextList) {
            label.setForeground(theme.getGeneralTextColor());
        }

        this.header.setForeground(theme.getGeneralTextColor());
        this.exitButton.setBackground(theme.getQuitGameButtonColor());
    }

    private class SoundButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            changeSound();
        }

    }

    /**
     * Small private class for theme button. Calls changeTheme() method when
     * pressed.
     */
    private class ThemeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeTheme();
        }
    }

    public void playSound(Sound sound) {
        sound.setAudioFile("src/main/java/com/team13/datanero/sounds/backgroundmusicdemo.wav");
        sound.start();
        sound.loop();
    }

    public void stopSound(Sound sound) {
        sound.stop();
    }
}