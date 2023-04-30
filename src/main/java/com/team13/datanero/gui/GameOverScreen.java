package com.team13.datanero.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.team13.datanero.backend.Game;
import com.team13.datanero.gui.Theme.FontStyle;

public class GameOverScreen extends JPanel {
    private JLabel messageLabel;
    private MainFrame mainFrame;
    private int score;
    private Theme theme;
    private ButtonActions buttonActions;

    public GameOverScreen(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.mainFrame = mainFrame;
        this.score = Game.getInstance().getScore();
        this.theme = Theme.getInstance();
        this.buttonActions = new ButtonActions(this.mainFrame);
        setBackground(theme.getScreenBackGroundColor());

        /* Add padding to the screen */
        Border borderPadding = BorderFactory.createEmptyBorder(300, 100, 0, 200);
        setBorder(borderPadding);

        /* Create and add rest of the elements */
        setMascot(gbc);
        setMessageLabel(gbc);
        setExitButton(gbc);
        setScoreInputButton(gbc);
    }

    private void setMascot(GridBagConstraints gbc) {
        /* Teacher mascot */
        ImageIcon mascotImage = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_terminated.png");
        JLabel mascot = new JLabel(mascotImage);

        /* Add mascot to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(80, 10, 30, 10);
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

    private void setExitButton(GridBagConstraints gbc) {
        /* Create exit button */
        JButton exitButton = new CustomButton("Palaa päävalikkoon", theme.getQuitGameButtonColor(), 32, FontStyle.BOLD);
        exitButton.setActionCommand("Palaa päävalikkoon");
        exitButton.setPreferredSize(new Dimension(500, 120));
        exitButton.setMaximumSize(new Dimension(500, 120));

        /* Define action for exit button */
        exitButton.addActionListener(this.buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(exitButton, gbc);
    }

    private void setScoreInputButton(GridBagConstraints gbc) {
        /* Create score input button */
        JButton scoreInputButton = new CustomButton("Tallenna pisteet", theme.getQuitGameButtonColor(), 32,
                FontStyle.BOLD);
        scoreInputButton.setActionCommand("Siirry syöttämään pisteet");
        scoreInputButton.setPreferredSize(new Dimension(500, 120));
        scoreInputButton.setMaximumSize(new Dimension(500, 120));

        /* Define action for score input button */
        scoreInputButton.addActionListener(this.buttonActions);

        /* Add the score input button to the grid */
        gbc.gridx = 1;
        gbc.gridy = 2;
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
        messageLabel.setText("Voi rähmä, peli päättyi! Pistesaaliisi on: " + this.score);
        messageLabel.setForeground(theme.getGeneralTextColor());
        System.out
                .println("Status: Game over screen message: Voi rähmä, peli päättyi! Pistesaaliisi on: " + this.score);
    }
}