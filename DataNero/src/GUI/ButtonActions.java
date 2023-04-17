package GUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ButtonActions implements ActionListener {
    private JPanel cards;

    public ButtonActions(JPanel cards) {
        this.cards = cards;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Aloita peli":
                // Switch to game screen
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, "gameScreen");
                break;
            case "Asetukset":
                // Settings logic
                break;
            case "Parhaat tulokset":
                // High scores logic
                break;
            case "Poistu":
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Unknown action command: " + command);
        }
    }
}
