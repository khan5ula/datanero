package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class MainMenu extends JPanel {
    public MainMenu() {
        setLayout(new GridBagLayout());

        // Placeholder for logo
        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png"));
        
        // Placeholder for mascot
        JLabel mascot = new JLabel(new ImageIcon("path/to/mascot.png"));

        // Buttons
        JButton startButton = new JButton("Aloita peli");
        JButton settingsButton = new JButton("Asetukset");
        JButton highScoresButton = new JButton("Parhaat tulokset");
        JButton exitButton = new JButton("Poistu");

        // Button action handling
        ButtonActions buttonActions = new ButtonActions();
        startButton.addActionListener(buttonActions);
        settingsButton.addActionListener(buttonActions);
        highScoresButton.addActionListener(buttonActions);
        exitButton.addActionListener(buttonActions);

        // Add components to panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logo, gbc);

        gbc.gridy = 1;
        add(mascot, gbc);

        gbc.gridy = 2;
        add(startButton, gbc);

        gbc.gridy = 3;
        add(settingsButton, gbc);

        gbc.gridy = 4;
        add(highScoresButton, gbc);

        gbc.gridy = 5;
        add(exitButton, gbc);
    }
}
