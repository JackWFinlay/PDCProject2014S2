package pyramid_solitare_jackfinlay;

import pyramid_solitare_jackfinlay.model.ui.CUI;

/**
 * The class called when the program is run.
 *
 * @author Jack Finlay ID: 1399273
 *
 */
public class Main {

    public static CUI cui;

    /**
     * The Main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cui = new CUI();
        cui.menu();
    }

}
