package com.team13.datanero.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.team13.datanero.backend.Game;

public class GameOverButtonActions implements ActionListener {
    private MainFrame mainFrame;

    public GameOverButtonActions(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Palaa päävalikkoon".equals(command)) {
            /* Switch back to the main menu and reset the game */
            mainFrame.switchTo("mainMenu");
            Game.resetInstance();
            mainFrame.setGame(Game.getInstance());
        } else {
            throw new IllegalArgumentException("Unknown action command: " + command);
        }
    }
}
