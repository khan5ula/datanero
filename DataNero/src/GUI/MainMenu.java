package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;

public class MainMenu extends JPanel {
    private MainFrame mainFrame;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        /* Logo */
        JLabel logo = new JLabel(new ImageIcon("DataNero/src/Images/datanero_logo.png"));
        
        /* Teacher mascot */
        ImageIcon originalMascotIcon = new ImageIcon("DataNero/src/Images/mascot.png");
        int originalWidth = originalMascotIcon.getIconWidth();
        int originalHeight = originalMascotIcon.getIconHeight();

        // Set the desired scale factor
        double scaleFactor = 0.5;

        // Calculate the new width and height
        int newWidth = (int) (originalWidth * scaleFactor);
        int newHeight = (int) (originalHeight * scaleFactor);

        // Create a new ImageIcon with the scaled image
        Image scaledImage = originalMascotIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledMascotIcon = new ImageIcon(scaledImage);

        // Set the new ImageIcon to the JLabel mascot
        JLabel mascot = new JLabel(scaledMascotIcon);
        
        /* Buttons */
        JButton startButton = new JButton("Aloita peli");
        JButton settingsButton = new JButton("Asetukset");
        JButton highScoresButton = new JButton("Parhaat tulokset");
        JButton exitButton = new JButton("Poistu");

        /* Style the buttons */
        styleButton(startButton);
        styleButton(settingsButton);
        styleButton(highScoresButton);
        styleButton(exitButton);

        /* Set individual colors for the buttons */
        startButton.setBackground(new Color(6, 214, 160));
        settingsButton.setBackground(new Color(38, 84, 124));
        highScoresButton.setBackground(new Color(255, 209, 102));
        exitButton.setBackground(new Color(239, 71, 111));

        /* Button action handling */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        startButton.addActionListener(buttonActions);
        settingsButton.addActionListener(buttonActions);
        highScoresButton.addActionListener(buttonActions);
        exitButton.addActionListener(buttonActions);

        /* Add components to panel */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Top and bottom gap of 10 pixels
        
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
        add(exitButton, gbc);    }

    /**
     * Method that applies a style to the main menu buttons
     * @param button JButton, the button object to be modified
     */
    private void styleButton(JButton button) {
        /* Set the font, text color and background color */
        button.setFont(new Font("Arial", Font.BOLD, 38));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 153, 255));

        /* Add a border with rounded corners */
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 117, 210), 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        /* Set the button size */
        button.setPreferredSize(new Dimension(800, 130));

        /* Enable painting with background color */
        button.setOpaque(true);

        /* Remove focus border */
        button.setFocusPainted(false);
    }

}