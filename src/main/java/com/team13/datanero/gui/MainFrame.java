package com.team13.datanero.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.team13.datanero.backend.Question;
import com.team13.datanero.backend.QuestionBank;
import com.team13.datanero.backend.Game;


import java.awt.CardLayout;
import java.awt.Color;

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

        /* Add main menu and game screen to cards */
        cards.add(mainMenu, "mainMenu");
        cards.add(gameScreen, "gameScreen");

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
        /* Lazy question feed */
        QuestionBank questionBank = QuestionBank.getInstance();
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
        question.setCorrectAnswer("CREATE TABLE");
        question.setFirstWrongAnswer("CREATE DATABASE");
        question.setSecondWrongAnswer("ALTER TABLE");
        question.setThirdWrongAnswer("INSERT INTO");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Millä seuraavista komennoista valitaan kaikki sarakkeet taulusta \"employees\"?");
        question.setCorrectAnswer("SELECT * FROM employees");
        question.setFirstWrongAnswer("SELECT ALL from employees");
        question.setSecondWrongAnswer("SELECT employees");
        question.setThirdWrongAnswer("SELECT FROM employees");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Millä seuraavista komennoista voidaan muokata olemassaolevaa SQL-taulua?");
        question.setCorrectAnswer("ALTER TABLE");
        question.setFirstWrongAnswer("UPDATE");
        question.setSecondWrongAnswer("CHANGE TABLE");
        question.setThirdWrongAnswer("MODIFY TABLE");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Mitä SQL-funktiota käytetään palauttamaan rivien lukumäärä taulusta?");
        question.setCorrectAnswer("COUNT()");
        question.setFirstWrongAnswer("MAX()");
        question.setSecondWrongAnswer("MIN()");
        question.setThirdWrongAnswer("AVG()");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Millä seuraavista SQL-komennoista lajitellaan arvot nousevaan tai laskevaan järjestykseen?");
        question.setCorrectAnswer("ORDER BY");
        question.setFirstWrongAnswer("GROUP BY");
        question.setSecondWrongAnswer("WHERE");
        question.setThirdWrongAnswer("HAVING");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Mikä seuraavista ei ole SQL-kielen aggregaattifunktio?");
        question.setCorrectAnswer("RANK()");
        question.setFirstWrongAnswer("SUM()");
        question.setSecondWrongAnswer("AVG()");
        question.setThirdWrongAnswer("MAX()");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Mikä on seuraavista relaatiomallinnuksessa käytettävä termi, joka kuvaa taulujen välistä yhteyttä?");
        question.setCorrectAnswer("Liitos (JOIN)");
        question.setFirstWrongAnswer("Yhdistäminen (MERGE)");
        question.setSecondWrongAnswer("Linkitys (LINK)");
        question.setThirdWrongAnswer("Solmu (NODE)");
        questionBank.addQuestion(question);

        question = new Question();
        question.setQuestion("Mikä on koosteattribuutti?");
        question.setCorrectAnswer("Attribuutti, joka muodostuu useammasta aliattribuutista.");
        question.setFirstWrongAnswer("Attribuutti, joka esiintyy vain yhdessä relaatiossa.");
        question.setSecondWrongAnswer("Attribuutti, joka esiintyy useammassa relaatiossa.");
        question.setThirdWrongAnswer("Attribuutti, joka on riippuvainen toisesta atribuutista.");
        questionBank.addQuestion(question);

        /* Start the game */
        Game game = Game.getInstance();
        new MainFrame(game);
    }
}
