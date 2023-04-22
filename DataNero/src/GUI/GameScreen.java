package GUI;

import javax.swing.*;

import Backend.Game;

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
        this.game.getNewQuestion();

        setLayout(new GridBagLayout());

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

        // Add question label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(questionLabel, gbc);

        // Add answer buttons
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        for (int i = 0; i < answerButtons.length; i++) {
            gbc.gridx = i % 2;
            gbc.gridy = 1 + i / 2;
            add(answerButtons[i], gbc);
        }

        // Add score label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(scoreLabel, gbc);

        // Add lives label
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(livesLabel, gbc);

        // Add exit button
        JButton lopetaButton = new JButton("Lopeta");
        lopetaButton.setActionCommand("Lopeta");

        ButtonActions buttonActions = new ButtonActions(this.mainFrame, game);
        lopetaButton.addActionListener(buttonActions);

        lopetaButton.setFont(new Font("Arial", Font.BOLD, 38));
        lopetaButton.setBackground(new Color(239, 71, 111));
        lopetaButton.setPreferredSize(new Dimension(800, 130));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(lopetaButton, gbc);
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
            } else {
                // Continue game
                game.getNewQuestion();
                updateGameDisplay();
            }
        }
    }
}