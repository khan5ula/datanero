package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.Game;
import Backend.Question;
import Backend.QuestionBank;

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
        Question question1 = new Question();
        question1.setQuestion("Mikä seuraavista ei ole normaalimuoto?");
        question1.setCorrectAnswer("CCNF");
        question1.setFirstWrongAnswer("1NF");
        question1.setSecondWrongAnswer("3NF");
        question1.setThirdWrongAnswer("BCNF");
        questionBank.addQuestion(question1);
        questionBank.addQuestion(new Question());

        Game game = new Game(3, questionBank);
        new MainFrame(game);
    }
}
