package pyramid_solitare_jackfinlay;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pyramid_solitare_jackfinlay.model.Game;
import pyramid_solitare_jackfinlay.model.Player;
import pyramid_solitare_jackfinlay.model.ui.GUI;

/**
 * The class called when the program is run.
 *
 * @author Jack Finlay ID: 1399273
 *
 */
public class Main {

    public static GUI gui;
    private static Game game;

    /**
     * The Main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } // Set Look and feel to the system's custom look and feel.

        game = new Game(new Player());

        gui = GUI.getGUI(game);

        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

        game.setPlayer(new Player(getPlayerName()));
        
        game.notifyObservers();
    }

    public static String getPlayerName() {
        String input;

        do {
            input = (JOptionPane.showInputDialog(null,
                    "Enter your name: ",
                    "Pyramid Solitaire", JOptionPane.PLAIN_MESSAGE));
        } while (input != null && input.length() == 0);

        // Cancel option selected:
        if (input == null) {
            System.exit(0);
        }

        return input;
    }
}
