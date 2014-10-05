package pyramid_solitare_jackfinlay;

import javax.swing.JOptionPane;
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

    /**
     * The Main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game;
        
        game = new Game(new Player(getPlayerName()));
        
        gui = new GUI( game );
        
        java.awt.EventQueue.invokeLater( new Runnable()
        {

            @Override
            public void run()
            {
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
            }
        });
    }

    public static String getPlayerName()
    {
        String input;

        do
        {
            input = (JOptionPane.showInputDialog(null,
                    "Enter your name: ",
                    "Pyramid Solitaire", JOptionPane.PLAIN_MESSAGE));
        } while (input != null && input.length() == 0);

        // Cancel option selected:
        if (input == null)
        {
            System.exit(0);
        }

        return input;
    }
}
