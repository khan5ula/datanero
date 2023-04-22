package com.team13.datanero.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.team13.datanero.backend.Question;
import com.team13.datanero.backend.QuestionBank;
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

        // Use CardLayout to switch between screens
        this.cards = new JPanel(new CardLayout());

        MainMenu mainMenu = new MainMenu(this);
        GameScreen gameScreen = new GameScreen(this, this.game);

        cards.add(mainMenu, "mainMenu");
        cards.add(gameScreen, "gameScreen");

        // Set the application icons
        //setIconImages(loadAppIcons());

        getContentPane().add(cards);
        setVisible(true);
    }

    public void switchTo(String cardName) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, cardName);
    }
    
    public static void main(String[] args) {
        /* Initialize the question bank with one question */
        QuestionBank questionBank = new QuestionBank();
        Question question = new Question();
        question.setQuestion("Mikä seuraavista ei ole normaalimuoto tietokantamallinnuksessa?");
        question.setCorrectAnswer("Absoluuttinen normaalimuoto (ANF)");
        question.setFirstWrongAnswer("Toinen normaalimuoto (2NF)");
        question.setSecondWrongAnswer("Kolmas normaalimuoto (3NF)");
        question.setThirdWrongAnswer("Boyce-Codd normaalimuoto (BCNF)");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Millä nimellä kutsutaan prosessia, jossa taulu hajautetaan pienemmiksi tauluiksi datan redundanssin vähentämiseksi?");
        question.setCorrectAnswer("Normalisointi");
        question.setFirstWrongAnswer("Indeksointi");
        question.setSecondWrongAnswer("Osittaminen");
        question.setThirdWrongAnswer("Partitiointi");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Millä komennolla luodaan uusi SQL-taulu?");
        question.setCorrectAnswer("Create table");
        question.setFirstWrongAnswer("Create database");
        question.setSecondWrongAnswer("Alter table");
        question.setThirdWrongAnswer("Insert into");
        questionBank.addQuestion(question);

        Game game = Game.getInstance(3, questionBank);
        new MainFrame(game);
    }
}
