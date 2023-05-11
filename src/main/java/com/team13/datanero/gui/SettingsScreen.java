package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.team13.datanero.gui.Sound.SoundStatus;
import com.team13.datanero.gui.Theme.FontStyle;
import com.team13.datanero.gui.Theme.ThemeType;

public class SettingsScreen extends JPanel {
    private MainFrame mainFrame;
    private JLabel header;
    private JButton musicButton;
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
        this.sound = Sound.getInstance();
        setBackground(theme.getScreenBackGroundColor());
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

        setHeader(gbc);

        /* Create options */
        String[] optionNames = { "Musiikki", "Peliäänet", "Kieli", "Vaikeustaso", "Teema" };
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

        for (int i = 0; i < 5; i++) {
            this.optionTextList.add(new JLabel(optionNames[i]));
            optionTextList.get(i).setFont(theme.getCustomFont(FontStyle.SEMIBOLD, 32));
            add(optionTextList.get(i), optionGbc);

            switch (i) {
                case 0: // Set music button
                    this.musicButton = new CustomButton("Päällä", Color.WHITE, 24, FontStyle.SEMIBOLD);
                    this.musicButton.setForeground(Color.BLACK);
                    this.musicButton.setPreferredSize(buttonDimension);
                    this.musicButton.setMaximumSize(buttonDimension);
                    this.musicButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    this.musicButton.addActionListener(new MusicButtonListener());
                    add(this.musicButton, optionButtonGbc);
                    this.optionButtons.add(this.musicButton);
                    break;
                case 1: // Set sound button
                    this.soundButton = new CustomButton("Päällä", Color.WHITE, 24, FontStyle.SEMIBOLD);
                    this.soundButton.setForeground(Color.BLACK);
                    this.soundButton.setPreferredSize(buttonDimension);
                    this.soundButton.setMaximumSize(buttonDimension);
                    this.soundButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    this.soundButton.addActionListener(new SoundButtonListener());
                    add(this.soundButton, optionButtonGbc);
                    this.optionButtons.add(this.soundButton);
                    break;
                case 2: // Set language button
                    this.languageButton = new CustomButton("Ei saatavilla", Color.WHITE, 24, FontStyle.SEMIBOLD);
                    this.languageButton.setForeground(Color.BLACK);
                    this.languageButton.setPreferredSize(buttonDimension);
                    this.languageButton.setMaximumSize(buttonDimension);
                    this.languageButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    add(this.languageButton, optionButtonGbc);
                    this.optionButtons.add(this.languageButton);
                    break;
                case 3: // Set difficulty button
                    this.difficultyButton = new CustomButton("Ei saatavilla", Color.WHITE, 24, FontStyle.SEMIBOLD);
                    this.difficultyButton.setForeground(Color.BLACK);
                    this.difficultyButton.setPreferredSize(buttonDimension);
                    this.difficultyButton.setMaximumSize(buttonDimension);
                    this.difficultyButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    add(this.difficultyButton, optionButtonGbc);
                    this.optionButtons.add(this.difficultyButton);
                    this.optionButtons.add(this.difficultyButton);
                    break;
                case 4: // Set theme button
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

        setExitButton(gbc, optionNames, padding);
    }

    /**
     * Method that creates header label to the screen and adds it to the grid.
     * 
     * @param gbc The gridbagconstraints used on this screen.
     */
    private void setHeader(GridBagConstraints gbc) {
        /* Create Header label */
        this.header = new JLabel("Asetukset");
        header.setFont(theme.getCustomFont(FontStyle.BOLD, 84));

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
    }

    private void setExitButton(GridBagConstraints gbc, String[] optionNames, Insets padding) {
        /* Create exit button */
        this.exitButton = new CustomButton("Palaa päävalikkoon", theme.getExitButtonColor(), 32, FontStyle.BOLD);
        this.exitButton.setActionCommand("Palaa päävalikkoon");
        this.exitButton.setPreferredSize(new Dimension(500, 120));
        this.exitButton.setMaximumSize(new Dimension(500, 120));

        /* Add hover effect for exit button */
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(theme.getExitButtonHoverColor());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(theme.getExitButtonColor());
                repaint();
            }
        });

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

    /**
     * Method that toggles background music playback On and Off.
     */
    private void toggleMusic() {
        if (musicButton.getText().equals("Pois päältä")) {
            System.out.println("Status: Player toggled background music on");
            sound.startBackgroundMusic();
            musicButton.setText("Päällä");
        } else {
            System.out.println("Status: Player toggled background music off");
            sound.stopBackgroundMusic();
            musicButton.setText("Pois päältä");
        }
    }

    /**
     * Method that switches game sounds On and Off.
     */
    private void changeSound() {
        if (soundButton.getText().equals("Pois päältä")) {
            sound.playButtonClickSound();
            System.out.println("Status: Player switched game sounds on");
            sound.setSoundStatus(SoundStatus.ON);
            soundButton.setText("Päällä");
        } else {
            sound.playButtonClickSound();
            System.out.println("Status: Player switched game sounds off");
            sound.setSoundStatus(SoundStatus.OFF);
            soundButton.setText("Pois päältä");
        }
    }

    /**
     * Method that changes the system theme between Light and Dark.
     */
    private void changeTheme() {
        /* If player chose dark theme */
        if (themeButton.getText().equals("Vaalea")) {
            sound.playButtonClickSound();
            System.out.println("Status: Player chose dark theme");
            theme.setCurrentTheme(ThemeType.DARK);
            themeButton.setText("Tumma");

            /* If player chose light theme */
        } else {
            sound.playButtonClickSound();
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
        this.exitButton.setBackground(theme.getExitButtonColor());
    }

    /**
     * Small private class for music button. Calls toggleMusic() method when
     * pressed.
     */
    private class MusicButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            toggleMusic();
        }
    }

    /**
     * Small private class for sound button. Calls toggleMusic() method when
     * pressed.
     */
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

}