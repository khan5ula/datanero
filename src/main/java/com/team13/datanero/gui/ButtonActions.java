package com.team13.datanero.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.team13.datanero.backend.Game;
import com.team13.datanero.gui.Sound.SoundStatus;

public class ButtonActions implements ActionListener {
    private MainFrame mainFrame;
    private Sound clickSound;

    public ButtonActions(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.clickSound = Sound.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Aloita peli":
                playClickSound(clickSound);
                this.mainFrame.switchTo("gameScreen");
                Game.getInstance();
                break;
            case "Asetukset":
                playClickSound(clickSound);
                this.mainFrame.switchTo("settingsScreen");
                break;
            case "Parhaat tulokset":
                playClickSound(clickSound);
                this.mainFrame.getHighScoreScreen().setTheme();
                this.mainFrame.getHighScoreScreen().sethighScoreResults();
                this.mainFrame.switchTo("highScoreScreen");
                break;
            case "Poistu":
                playClickSound(clickSound);
                System.exit(0);
                break;
            case "Siirry syöttämään pisteet":
                playClickSound(clickSound);
                this.mainFrame.getHighScoreEntryScreen().updateAndDisplayScore();
                this.mainFrame.switchTo("highScoreEntryScreen");
                break;
            case "Tallenna nimimerkki":
                playClickSound(clickSound);
                this.mainFrame.getHighScoreEntryScreen().submitScore();
                this.mainFrame.getHighScoreScreen().setTheme();
                this.mainFrame.getHighScoreScreen().sethighScoreResults();
                this.mainFrame.switchTo("highScoreScreen");
                break;
            case "Lopeta":
            case "Palaa päävalikkoon":
                playClickSound(clickSound);
                this.mainFrame.getMainMenu().setTheme();
                mainFrame.switchTo("mainMenu");
                Game.resetInstance();
                mainFrame.setGame(Game.getInstance());
                break;
            default:
                throw new IllegalArgumentException("Unknown action command: " + command);
        }
    }

    /**
     * Method that plays click sound effect.
     */
    private void playClickSound(Sound clickSound) {
        if (clickSound.getSoundStatus() == SoundStatus.ON) {
            clickSound.setAudioFile("src/main/java/com/team13/datanero/sounds/buttonclick.wav");
            clickSound.start();
        }
    }
}
