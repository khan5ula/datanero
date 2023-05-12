package com.team13.datanero.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import com.team13.datanero.gui.Theme.FontStyle;

public class MainMenu extends JPanel {
    private MainFrame mainFrame;
    private Theme theme;
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
        ImageIcon logoImage = new ImageIcon(getClass().getResource("/images/datanerologo.png"));
        JLabel logo = new JLabel(logoImage);

        /* Sub-title */
        this.subtitle = new JLabel("Tietojenkäsittelyn tietovisa");
        this.subtitle.setFont(theme.getCustomFont(FontStyle.SEMIBOLD, 48));

        /* Teacher mascot with a welcome message */
        String imagePath = "/images/dalle-versions/new-generation/with_textes/mascot-welcome.png";
        ImageIcon mascotImage = new ImageIcon(getClass().getResource(imagePath));
        JLabel mascot = new JLabel(mascotImage);

        /* Replace the speech bubble mascot with a plain one after a delay */
        String plainImagePath = "/images/dalle-versions/new-generation/plain/dalle-generated-teacher-5.png";
        URL plainImageUrl = getClass().getResource(plainImagePath);
        ImageIcon plainImage = new ImageIcon(plainImageUrl);

        Timer timer = new Timer(3500, new ActionListener() { // set the desired delay here
            @Override
            public void actionPerformed(ActionEvent e) {
                mascot.setIcon(plainImage);
            }
        });
        timer.setRepeats(false);
        timer.start();

        /* Create buttons */
        this.startButton = new CustomButton("Aloita peli", theme.getStartGameButtonColor(), 38, FontStyle.BOLD);
        this.startButton.setPreferredSize(new Dimension(500, 120));

        this.settingsButton = new CustomButton("Asetukset", theme.getSettingsButtonColor(), 38, FontStyle.BOLD);
        this.settingsButton.setPreferredSize(new Dimension(500, 120));

        this.highScoresButton = new CustomButton("Parhaat tulokset", theme.getHighScoreButtonColor(), 38,
                FontStyle.BOLD);
        this.highScoresButton.setPreferredSize(new Dimension(500, 120));

        this.exitButton = new CustomButton("Poistu", theme.getExitButtonColor(), 38, FontStyle.BOLD);
        this.exitButton.setPreferredSize(new Dimension(500, 120));

        /* Button action handling */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        startButton.addActionListener(buttonActions);
        settingsButton.addActionListener(buttonActions);
        highScoresButton.addActionListener(buttonActions);
        exitButton.addActionListener(buttonActions);

        /* Button hover handling */
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(theme.getStartGameButtonHoverColor());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(theme.getStartGameButtonColor());
                repaint();
            }
        });

        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingsButton.setBackground(theme.getSettingsButtonHoverColor());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsButton.setBackground(theme.getSettingsButtonColor());
                repaint();
            }
        });

        highScoresButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                highScoresButton.setBackground(theme.getHighScoreButtonHoverColor());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                highScoresButton.setBackground(theme.getHighScoreButtonColor());
                repaint();
            }
        });

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
        this.subtitle.setForeground(theme.getGeneralTextColor());
        this.startButton.setBackground(theme.getStartGameButtonColor());
        this.settingsButton.setBackground(theme.getSettingsButtonColor());
        this.highScoresButton.setBackground(theme.getHighScoreButtonColor());
        this.exitButton.setBackground(theme.getExitButtonColor());
    }
}