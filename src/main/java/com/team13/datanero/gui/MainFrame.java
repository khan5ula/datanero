package com.team13.datanero.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.team13.datanero.backend.QuestionParser;
import com.team13.datanero.backend.Score;
import com.team13.datanero.backend.Game;
import com.team13.datanero.backend.HighScore;

import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private JPanel cards;
    private Game game;
    private GameOverScreen gameOverScreen;
    private MainMenu mainMenu;
    private HighScoreScreen highScoreScreen;
    private HighScoreEntryScreen highScoreEntryScreen;

    public MainFrame(Game game) {
        setTitle("DataNero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        this.game = Game.getInstance();

        /* Use CardLayout to switch between screens */
        this.cards = new JPanel(new CardLayout());

        /* Initialize different screens */
        this.mainMenu = new MainMenu(this);
        GameScreen gameScreen = new GameScreen(this, this.game);
        this.gameOverScreen = new GameOverScreen(this);
        SettingsScreen settingsScreen = new SettingsScreen(this);
        this.highScoreScreen = new HighScoreScreen(this);
        this.highScoreEntryScreen = new HighScoreEntryScreen(this);

        /* Add main menu and game screen to cards */
        cards.add(mainMenu, "mainMenu");
        cards.add(gameScreen, "gameScreen");
        cards.add(gameOverScreen, "GameOverScreen");
        cards.add(settingsScreen, "settingsScreen");
        cards.add(highScoreScreen, "highScoreScreen");
        cards.add(highScoreEntryScreen, "highScoreEntryScreen");

        /* Set icons */
        // setIconImages(loadAppIcons());

        getContentPane().add(cards);
        setVisible(true);
    }

    /**
     * Method that switches the screen to the desired card.
     * 
     * @param cardName String, name of the card to switch to.
     */
    public void switchTo(String cardName) {
        System.out.println("Status: MainFrame moves player to screen: " + cardName);
        CardLayout cl = (CardLayout) (cards.getLayout());
        if (cardName.equals("GameOverScreen")) {
            this.gameOverScreen.updateAndDisplayScore();
            System.out.println("Status: Sent player score data to game over screen. Score: " + this.game.getScore());
        }
        cl.show(cards, cardName);
    }

    /**
     * Method that creates game screen.
     * 
     * @param game The game.
     */
    public void setGame(Game game) {
        this.game = game;
        GameScreen gameScreen = new GameScreen(this, game);
        cards.add(gameScreen, "gameScreen");
    }

    /**
     * Return the class variable Main Menu. Can be used to reach the Main Menu from
     * other classes such as ButtonActions.
     * 
     * @return Main Menu class variable from the Main Frame.
     */
    public MainMenu getMainMenu() {
        return this.mainMenu;
    }

    /**
     * Return the class variable High Score Screen. Can be used to reach the High Score Screen from
     * other classes such as ButtonActions.
     * 
     * @return Main Menu class variable from the Main Frame.
     */
    public HighScoreScreen getHighScoreScreen() {
        return this.highScoreScreen;
    }

    /**
     * Return the class variable High Score Entry Screen. Can be used to reach the High Score Entry Screen from
     * other classes such as ButtonActions.
     * 
     * @return Main Menu class variable from the Main Frame.
     */
    public HighScoreEntryScreen getHighScoreEntryScreen() {
        return this.highScoreEntryScreen;
    }

    /**
     * It's 106 miles to Chicago, we got a full tank of gas, half a pack of
     * cigarettes, it's dark... and we're wearing sunglasses.
     * 
     * @param args The sound you make when you rise from couch.
     */
    public static void main(String[] args) {
        /* Start the game */
        QuestionParser questionParser = new QuestionParser();
        questionParser.execute();
        Game game = Game.getInstance();
        new MainFrame(game);
    }
}
