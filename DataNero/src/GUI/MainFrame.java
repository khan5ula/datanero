package GUI;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("DataNero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        MainMenu mainMenu = new MainMenu();
        add(mainMenu);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
