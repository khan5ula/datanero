package com.team13.datanero.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.team13.datanero.backend.Game;
import com.team13.datanero.backend.HighScore;
import com.team13.datanero.gui.Theme.FontStyle;

public class GameOverScreen extends JPanel {
    private JLabel messageLabel;
    private JLabel scoreStatusLabel;
    private MainFrame mainFrame;
    private int score;
    private Theme theme;
    private ButtonActions buttonActions;
    private JButton scoreInputButton;
    private JButton exitButton;

    public GameOverScreen(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.mainFrame = mainFrame;
        this.score = Game.getInstance().getScore();
        this.theme = Theme.getInstance();
        this.buttonActions = new ButtonActions(this.mainFrame);
        setBackground(theme.getScreenBackGroundColor());

        /* Add padding to the screen */
        Border borderPadding = BorderFactory.createEmptyBorder(200, 100, 0, 250);
        setBorder(borderPadding);

        /* Create and add rest of the elements */
        setMascot(gbc);
        setMessageLabel(gbc);
        setScoreStatusLabel(gbc);
        setExitButton(gbc);
        setScoreInputButton(gbc);
    }

    private void setMascot(GridBagConstraints gbc) {
        /* Teacher mascot */
        String imagePath = "/images/dalle-versions/new-generation/dalle-generated-teacher-5.png";
        URL imagePathURL = getClass().getResource(imagePath);
        ImageIcon mascotImage = new ImageIcon(imagePathURL);
        JLabel mascot = new JLabel(mascotImage);

        /* Add mascot to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(mascot, gbc);
    }

    private void setMessageLabel(GridBagConstraints gbc) {
        /* Define message label */
        messageLabel = new JLabel();
        messageLabel.setFont(theme.getCustomFont(FontStyle.REGULAR, 48));

        /* Add messagelabel to the grid */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(messageLabel, gbc);
    }

    private void setScoreStatusLabel(GridBagConstraints gbc) {
        /* Define the label */
        this.scoreStatusLabel = new JLabel();
        this.scoreStatusLabel.setFont(theme.getCustomFont(FontStyle.REGULAR, 48));

        /* Add label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(scoreStatusLabel, gbc);
    }

    private void setExitButton(GridBagConstraints gbc) {
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
        this.exitButton.addActionListener(this.buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(this.exitButton, gbc);
    }

    private void setScoreInputButton(GridBagConstraints gbc) {
        /* Create score input button */
        this.scoreInputButton = new CustomButton("Tallenna pisteet", theme.getExitButtonColor(), 32,
                FontStyle.BOLD);
        this.scoreInputButton.setActionCommand("Siirry syöttämään pisteet");
        this.scoreInputButton.setPreferredSize(new Dimension(500, 120));
        this.scoreInputButton.setMaximumSize(new Dimension(500, 120));

        /* Define action for score input button */
        this.scoreInputButton.addActionListener(this.buttonActions);

        /* Add the score input button to the grid */
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        add(scoreInputButton, gbc);
    }

    /**
     * Method that gets a fresh Game instance and updates the score information to
     * game over screen.
     */
    public void updateAndDisplayScore() {
        setBackground(theme.getScreenBackGroundColor());
        this.score = Game.getInstance().getScore();
        this.scoreInputButton.setBackground(theme.getAnswerButtonColor());
        this.exitButton.setBackground(theme.getExitButtonColor());
        messageLabel.setText("Voi rähmä, peli päättyi! Pistesaaliisi on: " + this.score);
        messageLabel.setForeground(theme.getGeneralTextColor());

        /*
         * Enable high score input option if the player score is greater than 0 and:
         * 1. There is room in the scoreboard
         * 2. The player score is greater than the lowest score in the scoreboard
         */
        if (score > 0 && (HighScore.getInstance().getCount() < 5 || score > HighScore.getInstance().getLowestScore())) {
            this.scoreInputButton.setEnabled(true);
            this.scoreStatusLabel.setText("Hienoa! Pisteesi riittävät Parhaat Pisteet -tilastoon!");
            this.scoreStatusLabel.setForeground(theme.getGeneralTextColor());
        } else {
            this.scoreInputButton.setEnabled(false);
            this.scoreStatusLabel.setText("Höh! Pisteesi eivät riitä parhaat Pisteet -tilastoon!");
            this.scoreStatusLabel.setForeground(theme.getGeneralTextColor());
        }

        System.out
                .println("Status: Game over screen message: Voi rähmä, peli päättyi! Pistesaaliisi on: " + this.score);
    }
}