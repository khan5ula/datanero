package com.team13.datanero.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.team13.datanero.backend.Game;

public class ButtonActions implements ActionListener {
    private MainFrame mainFrame;
    private Sound sound;

    public ButtonActions(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.sound = Sound.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Aloita peli":
                sound.playButtonClickSound();
                this.mainFrame.switchTo("gameScreen");
                Game.getInstance();
                break;
            case "Asetukset":
                sound.playButtonClickSound();
                this.mainFrame.switchTo("settingsScreen");
                break;
            case "Parhaat tulokset":
                sound.playButtonClickSound();
                this.mainFrame.getHighScoreScreen().setTheme();
                this.mainFrame.getHighScoreScreen().sethighScoreResults();
                this.mainFrame.switchTo("highScoreScreen");
                break;
            case "Poistu":
                sound.playButtonClickSound();
                System.exit(0);
                break;
            case "Siirry syöttämään pisteet":
                sound.playButtonClickSound();
                this.mainFrame.getHighScoreEntryScreen().updateAndDisplayScore();
                this.mainFrame.switchTo("highScoreEntryScreen");
                break;
            case "Tallenna nimimerkki":
                sound.playButtonClickSound();
                this.mainFrame.getHighScoreEntryScreen().submitScore();
                this.mainFrame.getHighScoreScreen().setTheme();
                this.mainFrame.getHighScoreScreen().sethighScoreResults();
                this.mainFrame.switchTo("highScoreScreen");
                break;
            case "Lopeta":
            case "Palaa päävalikkoon":
                sound.playButtonClickSound();
                this.mainFrame.getMainMenu().setTheme();
                mainFrame.switchTo("mainMenu");
                Game.resetInstance();
                mainFrame.setGame(Game.getInstance());
                break;
            default:
                throw new IllegalArgumentException("Unknown action command: " + command);
        }
    }

}
