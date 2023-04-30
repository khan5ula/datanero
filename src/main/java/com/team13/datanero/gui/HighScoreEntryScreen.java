package com.team13.datanero.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team13.datanero.backend.Game;
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
        promptLabel.setFont(theme.getCustomFont(FontStyle.REGULAR, 32));

        /* Add prompt label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(promptLabel, gbc);
    }

    private void setInputField(GridBagConstraints gbc) {
        /* Define name input field */
        nameInput = new JTextField(20);
        nameInput.setFont(theme.getCustomFont(FontStyle.REGULAR, 24));
        nameInput.setPreferredSize(new Dimension(400, 60));
        nameInput.setMaximumSize(new Dimension(400, 60));
    
        /* Add name input field to the grid */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(20, 10, 10, 5);
        add(nameInput, gbc);
    }
    
    private void setSubmitButton(GridBagConstraints gbc) {
        /* Create submit button */
        submitButton = new CustomButton("Submit", theme.getQuitGameButtonColor(), 24, FontStyle.BOLD);
        submitButton.setActionCommand("Submit");
        submitButton.setPreferredSize(new Dimension(200, 60));
        submitButton.setMaximumSize(new Dimension(200, 60));
    
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
        this.score = Game.getInstance().getScore();
        System.out
                .println("Status: High score entry screen message: Enter your nickname. Your score is: " + this.score);
    }
}
