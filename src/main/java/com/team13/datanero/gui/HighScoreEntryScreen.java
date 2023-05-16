package com.team13.datanero.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.team13.datanero.backend.Game;
import com.team13.datanero.backend.HighScore;
import com.team13.datanero.backend.Score;
import com.team13.datanero.gui.Theme.FontStyle;

public class HighScoreEntryScreen extends JPanel {
    private JLabel promptLabel;
    private JTextField nameInput;
    private JButton submitButton;
    private MainFrame mainFrame;
    private int score;
    private Theme theme;

    public HighScoreEntryScreen(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.mainFrame = mainFrame;
        this.score = Game.getInstance().getScore();
        this.theme = Theme.getInstance();
        setBackground(theme.getScreenBackGroundColor());

        setPromptLabel(gbc);
        setInputField(gbc);
        setSubmitButton(gbc);
    }

    private void setPromptLabel(GridBagConstraints gbc) {
        /* Define prompt label */
        promptLabel = new JLabel("Syötä nimimerkkisi:");
        promptLabel.setFont(theme.getCustomFont(FontStyle.REGULAR, 44));

        /* Add prompt label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 40, 0);
        add(promptLabel, gbc);
    }

    private void setInputField(GridBagConstraints gbc) {
        /* Define name input field */
        this.nameInput = new JTextField(20);
        this.nameInput.setFont(theme.getCustomFont(FontStyle.RETINA, 32));
        this.nameInput.setPreferredSize(new Dimension(400, 80));
        this.nameInput.setMaximumSize(new Dimension(400, 80));
        this.nameInput.setMargin(new Insets(0, 10, 0, 0));

        /* Set character limit to the name input field */
        int maxLength = 15;
        this.nameInput.setDocument(new LengthRestrictedDocument(maxLength));

        /* Add name input field to the grid */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(20, 10, 10, 5);
        add(this.nameInput, gbc);
    }

    private void setSubmitButton(GridBagConstraints gbc) {
        /* Create submit button */
        submitButton = new CustomButton("Tallenna", theme.getAnswerButtonColor(), 24, FontStyle.BOLD);
        submitButton.setActionCommand("Tallenna nimimerkki");
        submitButton.setPreferredSize(new Dimension(200, 80));
        submitButton.setMaximumSize(new Dimension(200, 80));

        /* Add hover effect for submit button */
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(theme.getAnswerButtonHoverColor());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(theme.getAnswerButtonColor());
                repaint();
            }
        });

        /* Define action for submit button */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        submitButton.addActionListener(buttonActions);

        /* Add the submit button to the grid */
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(20, 5, 10, 10);
        add(submitButton, gbc);
    }

    /**
     * Method that gets a fresh Game instance and updates the score information to
     * high score entry screen.
     */
    public void updateAndDisplayScore() {
        setBackground(theme.getScreenBackGroundColor());
        this.promptLabel.setForeground(theme.getGeneralTextColor());
        this.submitButton.setBackground(theme.getAnswerButtonColor());
        this.score = Game.getInstance().getScore();
        System.out
                .println("Status: High score entry screen message: Enter your nickname. Your score is: " + this.score);
    }

    public void submitScore() {
        System.out.println("Status: Submitting the player nickname and adding the score to High Score Chart");
        this.score = Game.getInstance().getScore();
        Score scoreWithNickname = new Score(this.nameInput.getText(), score);
        HighScore.getInstance().addScore(scoreWithNickname);
        HighScore.getInstance().setScoresToDatabase();
        this.nameInput.setText("");
    }

    /**
     * Helper class for High Score Entry Screen.
     * This class is used to limit the number of characters player can enter to the
     * nickname input field.
     */
    private static class LengthRestrictedDocument extends PlainDocument {
        private final int limit;

        public LengthRestrictedDocument(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

}
