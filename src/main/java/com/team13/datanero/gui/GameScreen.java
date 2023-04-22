package com.team13.datanero.gui;

import javax.swing.*;

import com.team13.datanero.backend.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

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
        GridBagConstraints gbc = new GridBagConstraints();

        Insets padding = new Insets(5, 5, 5, 5);

        /* Set an empty border with with a padding around the panel */
        Border borderPadding = BorderFactory.createEmptyBorder(100, 100, 100, 100);
        setBorder(borderPadding);

        /* Create text area for the question */
        questionTextArea = new JTextArea(game.getCurrentQuestion());
        questionTextArea.setFont(new Font("Arial", Font.BOLD, 48));
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBackground(null);
        questionTextArea.setOpaque(false);
        questionTextArea.setFocusable(false);

        /* Create answer buttons */
        answerButtons = new JButton[4];
        String[] answers = game.getAnswersForCurrentQuestion();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new ButtonFactory(answers[i], 'L', Color.BLUE).getButton();
            answerButtons[i].addActionListener(new AnswerButtonListener(i));
        }

        /* Create a list of the answer buttons and shuffle it */
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(answerButtons[0]);
        buttons.add(answerButtons[1]);
        buttons.add(answerButtons[2]);
        buttons.add(answerButtons[3]);

        /* Shuffle the buttons */
        Collections.shuffle(buttons);

        /* Create labels for score and lives */
        scoreLabel = new JLabel("Pisteet: " + game.getScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        livesLabel = new JLabel("Elämät: " + game.getLives());
        livesLabel.setFont(new Font("Arial", Font.BOLD, 32));

        /* Create panel for score and lives */
        JPanel scoreAndLivesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints scoreAndLivesPanelConstraints = new GridBagConstraints();

        /* Add score label */
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 0;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(scoreLabel, scoreAndLivesPanelConstraints);

        /* Add lives label */
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 1;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(livesLabel, scoreAndLivesPanelConstraints);

        /* Add score & lives label to the grid */
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

        /* Add answer buttons to grid */
        gbc.gridwidth = 3; // Each button occupies 3 columns
        gbc.gridheight = 2; // Each button occupies 2 rows
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH; // Make the component fill the grid cell
        gbc.insets = new Insets(2, 5, 2, 5); // Reduced vertical space
        for (int i = 0; i < buttons.size(); i++) {
            gbc.gridx = 3 + (i % 2) * 3; // Calculate the gridx value for each button
            gbc.gridy = 4 + (i / 2) * 2; // Place the buttons below the question
            add(buttons.get(i), gbc);
        }

        /* Create exit button */
        JButton lopetaButton = new ButtonFactory("Lopeta", 'L', new Color(239, 71, 111)).getButton();
        lopetaButton.setActionCommand("Lopeta");

        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        lopetaButton.addActionListener(buttonActions);

        gbc.gridx = 10;
        gbc.gridy = 8;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.gridheight = 1; // Span 2 rows
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.NONE; // Don't make the component fill the grid cell
        gbc.insets = padding;
        add(lopetaButton, gbc);

        /* Load the mascot image */
        ImageIcon mascotIcon = new ImageIcon("src/main/java/com/team13/datanero/images/mascot.png");

        /* Create JLabel to hold the image, and set the icon */
        JLabel mascotLabel = new JLabel();
        mascotLabel.setIcon(mascotIcon);

        /* Add the Mascot JLabel to grid */
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

            if (game.getLives() <= 0 || !game.areQuestionsAvailable()) {
                /* Show the GameOverDialog with the score */
                GameOverScreen gameOverDialog = new GameOverScreen(mainFrame, game.getScore());
                gameOverDialog.setVisible(true);

                /* Switch back to the main menu and reset the game */
                mainFrame.switchTo("mainMenu");
                Game.resetInstance();
                mainFrame.setGame(Game.getInstance());
            } else {
                /* There are questions available, continue game */
                game.getNewQuestion();
                updateGameDisplay();
            }
        }
    }
}