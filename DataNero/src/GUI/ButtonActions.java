package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActions implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Aloita peli":
                // Start game logic
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
