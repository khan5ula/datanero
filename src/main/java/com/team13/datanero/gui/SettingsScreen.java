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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class SettingsScreen extends JPanel {
    private MainFrame mainFrame;

    public SettingsScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets padding = new Insets(10, 10, 10, 10);

        /* Add padding to the screen */
        Border borderPadding = BorderFactory.createEmptyBorder(400, 100, 0, 100);
        setBorder(borderPadding);

        /* Load the custom font from file */
        File font_file = new File("src/main/java/com/team13/datanero/fonts/FiraCode-Bold.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Create Header label */
        JLabel header = new JLabel("Asetukset");
        Font customFont = font.deriveFont(Font.PLAIN, 84);
        header.setFont(customFont);

        /* Add Header label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 200, 10);
        add(header, gbc);

        /* Create options */
        String[] optionNames = { "Ääni", "Kieli", "Vaikeus", "Teema" };
        GridBagConstraints optionGbc = new GridBagConstraints();
        optionGbc.gridx = 0;
        optionGbc.gridy = 1;
        optionGbc.gridwidth = 1;
        optionGbc.gridheight = 1;
        optionGbc.weightx = 1.0;
        optionGbc.weighty = 0.0;
        optionGbc.anchor = GridBagConstraints.LINE_END;
        optionGbc.insets = padding;

        GridBagConstraints optionButtonGbc = new GridBagConstraints();
        optionButtonGbc.gridx = 1;
        optionButtonGbc.gridy = 1;
        optionButtonGbc.gridwidth = 1;
        optionButtonGbc.gridheight = 1;
        optionButtonGbc.weightx = 1.0;
        optionButtonGbc.weighty = 0.0;
        optionButtonGbc.anchor = GridBagConstraints.LINE_START;
        optionButtonGbc.insets = padding;

        Font customFont32 = font.deriveFont(Font.PLAIN, 32);
        Dimension buttonDimension = new Dimension(250, 60);

        String[] optionButtonDefaultLabels = {"Ei saatavilla", "Ei saatavilla", "Ei saatavilla", "Vaalea"};

        for (int i = 0; i < optionButtonDefaultLabels.length; i++) {
            JLabel optionLabel = new JLabel(optionNames[i]);
            optionLabel.setFont(customFont32);
            add(optionLabel, optionGbc);

            JButton optionButton = new CustomButton(optionButtonDefaultLabels[i], Color.WHITE, 24);
            optionButton.setForeground(Color.BLACK);
            optionButton.setPreferredSize(buttonDimension);
            optionButton.setMaximumSize(buttonDimension);
            optionButton.setHorizontalAlignment(SwingConstants.RIGHT);
            add(optionButton, optionButtonGbc);

            optionGbc.gridy++;
            optionButtonGbc.gridy++;
        }

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
        gbc.gridy = optionNames.length + 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = padding;
        add(exitButton, gbc);
    }
}
