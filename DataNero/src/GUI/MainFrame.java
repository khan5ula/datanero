package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("DataNero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Use CardLayout to switch between screens
        JPanel cards = new JPanel(new CardLayout());

        MainMenu mainMenu = new MainMenu(cards);
        GameScreen gameScreen = new GameScreen();

        cards.add(mainMenu, "mainMenu");
        cards.add(gameScreen, "gameScreen");

        // Set the application icons
        //setIconImages(loadAppIcons());

        getContentPane().add(cards);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainFrame();
    }
}
