package com.team13.datanero.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.team13.datanero.backend.Question;
import com.team13.datanero.backend.QuestionBank;
import com.team13.datanero.backend.QuestionParser;
import com.team13.datanero.backend.Game;


import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private JPanel cards;
    private Game game;

    public MainFrame(Game game) {
        setTitle("DataNero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        this.game = game;

        /* Use CardLayout to switch between screens */
        this.cards = new JPanel(new CardLayout());

        /* Initialize main menu and game screen */
        MainMenu mainMenu = new MainMenu(this);
        GameScreen gameScreen = new GameScreen(this, this.game);
        GameOverScreen gameOverScreen = new GameOverScreen(this, this.game.getScore());

        /* Add main menu and game screen to cards */
        cards.add(mainMenu, "mainMenu");
        cards.add(gameScreen, "gameScreen");
        cards.add(gameOverScreen, "GameOverScreen");

        /* Set icons */
        //setIconImages(loadAppIcons());

        getContentPane().add(cards);
        setVisible(true);
    }

    /**
     * Method that switches the screen to the desired card.
     * @param cardName String, name of the card to switch to.
     */
    public void switchTo(String cardName) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, cardName);
    }

    public void setGame(Game game) {
        this.game = game;
        GameScreen gameScreen = new GameScreen(this, game);
        cards.add(gameScreen, "gameScreen");
    }    
    
    public static void main(String[] args) {

        /* Start the game */
        QuestionParser questionParser = new QuestionParser();
        questionParser.execute();
        Game game = Game.getInstance();
        new MainFrame(game);
    }
}
