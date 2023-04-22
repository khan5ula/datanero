package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private JPanel cards;

    public MainFrame() {
        setTitle("DataNero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Use CardLayout to switch between screens
        this.cards = new JPanel(new CardLayout());

        MainMenu mainMenu = new MainMenu(this);
        GameScreen gameScreen = new GameScreen(this, null);

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
        new MainFrame();
    }
}
