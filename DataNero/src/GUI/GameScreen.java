package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class GameScreen extends JPanel {
    private JPanel cards;

    public GameScreen() {
        this.cards = cards;

        setLayout(new GridBagLayout());

        /**
         * Teacher maskot.
         * This will later be swapped with a maskot that changes it's expression based on the player progress.
         */
        JLabel mascot = new JLabel(new ImageIcon("path/to/mascot.png"));

        /* Button for exiting the game */
        JButton exitButton = new JButton("Lopeta");
        exitButton.setFont(new Font("Arial", Font.BOLD, 38));
        exitButton.setBackground(new Color(239, 71, 111));
        exitButton.setPreferredSize(new Dimension(800, 130));

        /* Add components to the panel */
        GridBagConstraints gbc = new GridBagConstraints();
        // Configure the GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Assign maximum weight for horizontal space distribution
        gbc.weighty = 1.0; // Assign maximum weight for vertical space distribution
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Anchor the button to the bottom-right corner
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding around the button
        add(exitButton, gbc);
    }
}
