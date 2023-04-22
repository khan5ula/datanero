package com.team13.datanero.gui;

import javax.swing.*;

import com.team13.datanero.backend.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class GameScreen extends JPanel {
    private MainFrame mainFrame;
    private Game game;
    private JButton[] answerButtons;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JTextArea questionTextArea;

    public GameScreen(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;

        setLayout(new GridBagLayout());

        Insets padding = new Insets(5, 5, 5, 5);

        // Set an empty border with a 20-pixel padding around the panel
        Border borderPadding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        setBorder(borderPadding);

        // Create and configure components
        questionTextArea = new JTextArea(game.getCurrentQuestion());
        questionTextArea.setFont(new Font("Arial", Font.BOLD, 48));
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBackground(null);
        questionTextArea.setOpaque(false);
        questionTextArea.setFocusable(false);
        

        answerButtons = new JButton[4];
        String[] answers = game.getAnswersForCurrentQuestion();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new ButtonFactory(answers[i], 'L', Color.BLUE).getButton();
            answerButtons[i].addActionListener(new AnswerButtonListener(i));
        }

        scoreLabel = new JLabel("Pisteet: " + game.getScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));

        livesLabel = new JLabel("Elämät: " + game.getLives());
        livesLabel.setFont(new Font("Arial", Font.BOLD, 32));

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
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.gridheight = 2; // Span 2 rows
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH; // Make the component fill the grid cell
        gbc.insets = padding;
        add(scoreAndLivesPanel, gbc);
        
        /* Add the question label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 9; // Span the entire width
        gbc.gridheight = 3; // Occupy the top 4 rows
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH; // Make the component fill the grid cell
        gbc.insets = padding;
        add(questionTextArea, gbc);

        // Add answer buttons
        gbc.gridwidth = 3; // Each button occupies 3 columns
        gbc.gridheight = 2; // Each button occupies 2 rows
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH; // Make the component fill the grid cell
        gbc.insets = new Insets(2, 5, 2, 5); // Reduced vertical space
        for (int i = 0; i < answerButtons.length; i++) {
            gbc.gridx = 3 + (i % 2) * 3; // Calculate the gridx value for each button
            gbc.gridy = 4 + (i / 2) * 2; // Place the buttons below the question
            add(answerButtons[i], gbc);
        }

        // Add exit button
        JButton lopetaButton = new ButtonFactory("Lopeta", 'L', new Color(239, 71, 111)).getButton();
        lopetaButton.setActionCommand("Lopeta");

        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        lopetaButton.addActionListener(buttonActions);

        gbc.gridx = 10;
        gbc.gridy = 8;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.gridheight = 2; // Span 2 rows
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.NONE; // Don't make the component fill the grid cell
        gbc.insets = padding;
        add(lopetaButton, gbc);
        

        // Load the mascot image
        ImageIcon mascotIcon = new ImageIcon("src/main/java/com/team13/datanero/images/mascot.png");

        // Create a JLabel to hold the image and set the icon
        JLabel mascotLabel = new JLabel();
        mascotLabel.setIcon(mascotIcon);

        // Add the JLabel to the GameScreen panel
        gbc.gridx = 9; // Adjust the x-coordinate as needed
        gbc.gridy = 2; // Adjust the y-coordinate as needed
        gbc.gridwidth = 3; // You can adjust the width as needed
        gbc.gridheight = 10; // You can adjust the height as needed
        gbc.anchor = GridBagConstraints.CENTER; // Center the mascot image
        gbc.insets = padding;
        add(mascotLabel, gbc);
    }

    private void updateGameDisplay() {
        String[] answers = game.getAnswersForCurrentQuestion();
        questionTextArea.setText(game.getCurrentQuestion());
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
                game.incrementScore();
            } else {
                game.decrementLives();
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