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
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.team13.datanero.backend.Game;

public class GameScreen extends JPanel {
    private MainFrame mainFrame;
    private Game game;
    private JButton[] answerButtons;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel heartLabel1, heartLabel2, heartLabel3;
    private JTextPane questionTextArea;
    private JLabel mascotLabel;
    private int initialButtonWidth;
    private ArrayList<JButton> buttons;
    private boolean wasAnswerCorrect;

    public GameScreen(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;
        this.mascotLabel = new JLabel();

        /* Initialize the layout manager */
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets padding = new Insets(10, 10, 10, 10);

        /* Set an empty border with with a padding around the panel */
        Border borderPadding = BorderFactory.createEmptyBorder(250, 100, 100, 100);
        setBorder(borderPadding);

        /* Load custom font from file */
        File font_file = new File("src/main/java/com/team13/datanero/fonts/FiraCode-Light.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException e) {
            System.out.println("Error: Problem with custom font format: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: IOException occured with custom font: " + e.getMessage());
            e.printStackTrace();
        }

        /* Create text area for the question */
        questionTextArea = new JTextPane();
        questionTextArea.setText(game.getCurrentQuestion());
        questionTextArea.setFont(font.deriveFont(Font.PLAIN, 48));
        questionTextArea.setOpaque(false);
        questionTextArea.setEditable(false);
        questionTextArea.setFocusable(false);
        questionTextArea.setPreferredSize(new Dimension(1200, 150));
        questionTextArea.setMaximumSize(new Dimension(1200, 150));

        /* Center the question text */
        StyledDocument doc = questionTextArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        /* Create answer buttons */
        this.answerButtons = new JButton[4];
        String[] answers = game.getAnswersForCurrentQuestion();
        int textWidth = (int) (this.initialButtonWidth * 0.9);
        for (int i = 0; i < this.answerButtons.length; i++) {
            String buttonText = String.format("<html><body style=width: %dpx'>%s</body></html>",
                    textWidth, answers[i]);
            this.answerButtons[i] = new CustomButton(buttonText, Color.darkGray, 32);
            this.answerButtons[i].addActionListener(new AnswerButtonListener(i));
            this.answerButtons[i].setPreferredSize(new Dimension(800, 300));
            this.answerButtons[i].setMaximumSize(new Dimension(800, 300));
        }

        /* Create a list of the answer buttons and shuffle it */
        this.buttons = new ArrayList<>();
        buttons.add(this.answerButtons[0]);
        buttons.add(this.answerButtons[1]);
        buttons.add(this.answerButtons[2]);
        buttons.add(this.answerButtons[3]);

        /* Shuffle the buttons */
        Collections.shuffle(this.buttons);

        /* Create labels for score and lives */
        scoreLabel = new JLabel("Pisteet: " + game.getScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        // livesLabel = new JLabel("Elämät: " + (countHearts(game.getLives())));
        livesLabel = new JLabel("Elämät: ");
        livesLabel.setFont(new Font("Arial", Font.BOLD, 32));

        /* Create panel for score and lives */
        JPanel scoreAndLivesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints scoreAndLivesPanelConstraints = new GridBagConstraints();

        /* Add score label to the panel */
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 0;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(scoreLabel, scoreAndLivesPanelConstraints);

        /* Add lives label to the panel */
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 1;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(livesLabel, scoreAndLivesPanelConstraints);

        /* Create labels for hearts */
        ImageIcon heartIcon = new ImageIcon("src/main/java/com/team13/datanero/images/heart.png");
        this.heartLabel1 = new JLabel(heartIcon);
        this.heartLabel2 = new JLabel(heartIcon);
        this.heartLabel3 = new JLabel(heartIcon);

        /* Add heart labels to the panel */
        scoreAndLivesPanelConstraints.gridx = 1;
        scoreAndLivesPanelConstraints.gridy = 1;
        scoreAndLivesPanel.add(heartLabel1, scoreAndLivesPanelConstraints);

        scoreAndLivesPanelConstraints.gridx = 2;
        scoreAndLivesPanel.add(heartLabel2, scoreAndLivesPanelConstraints);

        scoreAndLivesPanelConstraints.gridx = 3;
        scoreAndLivesPanel.add(heartLabel3, scoreAndLivesPanelConstraints);

        /* Add score & lives label to the grid */
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 80, 10, 10);
        add(scoreAndLivesPanel, gbc);

        /* Add the question label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 9;
        gbc.gridheight = 3;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = padding;
        add(questionTextArea, gbc);

        /* Add answer buttons to grid */
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        Collections.shuffle(this.buttons);
        updateAnswerButtonLayout();

        /* Store answer button size to class variable for later use */
        this.initialButtonWidth = buttons.get(0).getWidth();

        /* Create exit button */
        JButton exitButton = new CustomButton("Lopeta", new Color(239, 71, 111), 32);
        exitButton.setActionCommand("Lopeta");
        exitButton.setPreferredSize(new Dimension(150, 100));
        exitButton.setMaximumSize(new Dimension(150, 100));

        /* Define action for exit button */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        exitButton.addActionListener(buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 10;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 80, 10, 10);
        add(exitButton, gbc);

        /* Load the mascot image */
        ImageIcon mascotIcon = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_happy.png");

        /* Create JLabel to hold the image, and set the icon */
        this.mascotLabel.setIcon(mascotIcon);

        /* Add the Mascot JLabel to grid */
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 10;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 80, 10, 10);
        add(mascotLabel, gbc);
    }

    /**
     * Method that updates the game screen elements.
     * Should be called after every turn to initialize a new turn.
     */
    private void updateGameDisplay() {
        System.out.println("Status: Updating game display");
        int textWidth = (int) (this.initialButtonWidth * 0.9);
        String[] answers = game.getAnswersForCurrentQuestion();
        questionTextArea.setText(game.getCurrentQuestion());
        for (int i = 0; i < this.answerButtons.length; i++) {
            String buttonText = String.format("<html><body style=width: %dpx'>%s</body></html>",
                    textWidth, answers[i]);
            this.answerButtons[i].setText(buttonText);
        }
        Collections.shuffle(this.buttons);
        updateAnswerButtonLayout();
        scoreLabel.setText("Pisteet: " + game.getScore());
        updateHearts(game.getLives());
        if (this.wasAnswerCorrect) {
            updatePositiveMascot();
        } else {
            updateNegativeMascot();
        }
    }

    /**
     * Method that updates the heart icon life indicators based on the players
     * lives.
     */
    private void updateHearts(int lives) {
        ImageIcon heartlessIcon = new ImageIcon("src/main/java/com/team13/datanero/images/heartless.png");
        switch (lives) {
            case 2:
                this.heartLabel3.setIcon(heartlessIcon);
                break;
            case 1:
                this.heartLabel2.setIcon(heartlessIcon);
                break;
            case 0:
                this.heartLabel1.setIcon(heartlessIcon);
                break;
        }
    }

    /**
     * Method that updates the layout of answer buttons.
     * Should be used inside updateGameDisplay() method to refresh
     * the buttons after they've been shuffled.
     */
    private void updateAnswerButtonLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        Insets padding = new Insets(15, 15, 15, 15);
        gbc.insets = padding;

        for (int i = 0; i < buttons.size(); i++) {
            gbc.gridx = 3 + (i % 2) * 3; // Calculate the gridx value for each button
            gbc.gridy = 4 + (i / 2) * 2; // Place the buttons below the question
            add(buttons.get(i), gbc);
        }
    }

    /**
     * Method that updates the game mascot based on the success of the player.
     */
    private void updateNegativeMascot() {
        String imagePath;
        switch (game.getLives()) {
            case 3:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_happy.png";
                break;
            case 2:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_worried.png";
                break;
            case 1:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_terrified.png";
                break;
            case 0:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_terminated.png";
                break;
            default:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_happy.png";
        }

        ImageIcon newMascotIcon = new ImageIcon(imagePath);
        this.mascotLabel.setIcon(newMascotIcon);
    }

    /**
     * Method that changes the mascot to happy one.
     */
    private void updatePositiveMascot() {
        String imagePath = "src/main/java/com/team13/datanero/images/mascot_happy.png";
        ImageIcon newMascotIcon = new ImageIcon(imagePath);
        this.mascotLabel.setIcon(newMascotIcon);
    }

    /**
     * Private class used by Game Class.
     * Contains the actions for pressing buttons.
     */
    private class AnswerButtonListener implements ActionListener {
        private int answerIndex;

        public AnswerButtonListener(int answerIndex) {
            this.answerIndex = answerIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            wasAnswerCorrect = game.submitAnswer(answerIndex);
            if (wasAnswerCorrect) {
                game.incrementScore();
            } else {
                game.decrementLives();
            }

            if (game.getLives() <= 0 || !game.areQuestionsAvailable()) {
                if (game.getLives() == 0) {
                    System.out.println("Status: Player has 0 lives left. Game over.");
                    updateNegativeMascot();
                    livesLabel.setText("Elämät:");
                    scoreLabel.setText("Pisteet: " + game.getScore());
                }
                /* Show the GameOverDialog with the score */
                mainFrame.switchTo("GameOverScreen");
                System.out.println("Status: Switching to game over screen");
            } else {
                /* There are questions available, continue game */
                game.getNewQuestion();
                updateGameDisplay();
            }
        }
    }
}