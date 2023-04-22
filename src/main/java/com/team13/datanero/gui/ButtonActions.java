package com.team13.datanero.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.team13.datanero.backend.Game;

public class ButtonActions implements ActionListener {
    private MainFrame mainFrame;

    public ButtonActions(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Aloita peli":
                // Switch to game screen
                mainFrame.switchTo("gameScreen");
                Game.getInstance();
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
                Game.resetInstance();
                mainFrame.setGame(Game.getInstance());
                break;
            default:
                throw new IllegalArgumentException("Unknown action command: " + command);
        }
    }
}
