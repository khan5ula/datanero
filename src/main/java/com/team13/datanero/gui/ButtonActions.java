package com.team13.datanero.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.team13.datanero.backend.Game;

public class ButtonActions implements ActionListener {
    private MainFrame mainFrame;
    private Game game;

    public ButtonActions(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Aloita peli":
                // Switch to game screen
                mainFrame.switchTo("gameScreen");
                break;
            case "Asetukset":
                // Settings logic
                break;
            case "Parhaat tulokset":
                // High scores logic
                break;
            case "Poistu":
                System.exit(0);
                break;
            case "Lopeta":
                mainFrame.switchTo("mainMenu");
                break;
            default:
                throw new IllegalArgumentException("Unknown action command: " + command);
        }
    }
}
