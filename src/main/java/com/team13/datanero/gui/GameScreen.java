package com.team13.datanero.gui;

import javax.swing.*;

import com.team13.datanero.backend.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel {
    private MainFrame mainFrame;
    private Game game;
    private JLabel questionLabel;
    private JButton[] answerButtons;
    private JLabel scoreLabel;
    private JLabel livesLabel;

    public GameScreen(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;

        setLayout(new GridBagLayout());

        Insets padding = new Insets(5, 5, 5, 5);

        // Create and configure components
        questionLabel = new JLabel(game.getCurrentQuestion());
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));

        answerButtons = new JButton[4];
        String[] answers = game.getAnswersForCurrentQuestion();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new ButtonFactory(answers[i], 'L', Color.BLUE).getButton();
            answerButtons[i].addActionListener(new AnswerButtonListener(i));
        }

        scoreLabel = new JLabel("Pisteet: " + game.getScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));

        livesLabel = new JLabel("Elämät: " + game.getLives());
        livesLabel.setFont(new Font("Arial", Font.BOLD, 18));

        GridBagConstraints gbc = new GridBagConstraints();

        // Create score and lives panel
        JPanel scoreAndLivesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints scoreAndLivesPanelConstraints = new GridBagConstraints();

        // Add score label
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 0;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(scoreLabel, scoreAndLivesPanelConstraints);

        // Add lives label
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 1;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(livesLabel, scoreAndLivesPanelConstraints);

        // Add the score and lives panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = padding;
        add(scoreAndLivesPanel, gbc);


        // Add question label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.34;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = padding;
        add(questionLabel, gbc);

        // Add answer buttons
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.66;
        gbc.insets = new Insets(2, 5, 2, 5); // Reduced vertical space
        for (int i = 0; i < answerButtons.length; i++) {
            gbc.gridx = i % 2;
            gbc.gridy = 1 + i / 2;
            add(answerButtons[i], gbc);
        }

        // Add exit button
        JButton lopetaButton = new ButtonFactory("Lopeta", 'L', new Color(239, 71, 111)).getButton();
        lopetaButton.setActionCommand("Lopeta");

        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        lopetaButton.addActionListener(buttonActions);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = padding;
        add(lopetaButton, gbc);

        // Load the mascot image
        ImageIcon mascotIcon = new ImageIcon("src/main/java/com/team13/datanero/images/mascot.png");

        // Create a JLabel to hold the image and set the icon
        JLabel mascotLabel = new JLabel();
        mascotLabel.setIcon(mascotIcon);

        // Add the JLabel to the GameScreen panel
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER; // Set gridheight to span all remaining rows
        gbc.anchor = GridBagConstraints.CENTER; // Center the mascot image vertically
        gbc.insets = padding;
        add(mascotLabel, gbc);


    }

    private void updateGameDisplay() {
        String[] answers = game.getAnswersForCurrentQuestion();
        questionLabel.setText(game.getCurrentQuestion());
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(answers[i]);
        }
        scoreLabel.setText("Pisteet: " + game.getScore());
        livesLabel.setText("Elämät: " + game.getLives());
    }

    private class AnswerButtonListener implements ActionListener {
        private int answerIndex;

        public AnswerButtonListener(int answerIndex) {
            this.answerIndex = answerIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean correct = game.submitAnswer(answerIndex);
            if (correct) {
                // Handle correct answer
                // e.g., show a message or update some UI element
            } else {
                // Handle incorrect answer
                // e.g., show a message or update some UI element
            }

            if (game.getLives() <= 0) {
                // End game
                // e.g., show a game over message, switch to another screen, etc.
            } else if (game.areQuestionsAvailable()) {
                // Continue game
                game.getNewQuestion();
                updateGameDisplay();
            } else {
                // No more questions available
                // e.g., show a message, switch to another screen, etc.
            }
        }
    }
}