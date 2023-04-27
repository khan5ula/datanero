package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.team13.datanero.backend.Game;

public class GameOverScreen extends JPanel {
    private JLabel messageLabel;
    private MainFrame mainFrame;
    private int score;
    private Theme theme;

    public GameOverScreen(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.mainFrame = mainFrame;
        this.score = Game.getInstance().getScore();
        Insets padding = new Insets(80, 10, 30, 10);
        this.theme = Theme.getInstance();
        setBackground(theme.getScreenBackGroundColor());

        /* Load the custom font from file */
        File font_file = new File("src/main/java/com/team13/datanero/fonts/FiraCode-Light.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Define message label */
        Font customFont = font.deriveFont(Font.PLAIN, 48);
        messageLabel = new JLabel();
        messageLabel.setFont(customFont);

        /* Add messagelabel to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(messageLabel, gbc);

        /* Add padding to the screen */
        Border borderPadding = BorderFactory.createEmptyBorder(400, 100, 0, 100);
        setBorder(borderPadding);

        /* Teacher mascot */
        ImageIcon mascotImage = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_terminated.png");
        JLabel mascot = new JLabel(mascotImage);
        
        /* Add mascot to the grid */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = padding;
        add(mascot, gbc);

        /* Create exit button */
        JButton exitButton = new CustomButton("Palaa päävalikkoon", new Color(239, 71, 111), 32);
        exitButton.setActionCommand("Palaa päävalikkoon");
        exitButton.setPreferredSize(new Dimension(500, 120));
        exitButton.setMaximumSize(new Dimension(500, 120));

        /* Define action for exit button */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        exitButton.addActionListener(buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(exitButton, gbc);
    }

    /**
     * Method that gets a fresh Game instance and updates the score information to game over screen.
     */
    public void updateAndDisplayScore() {
        this.score = Game.getInstance().getScore();
        messageLabel.setText("Voi rähmä, peli päättyi! Pistesaaliisi on: " + this.score);
        System.out.println("Status: Game over screen message: Voi rähmä, peli päättyi! Pistesaaliisi on: " + this.score);
    }
}