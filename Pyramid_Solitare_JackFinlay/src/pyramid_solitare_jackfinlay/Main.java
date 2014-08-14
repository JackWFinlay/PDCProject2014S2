/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import static pyramid_solitare_jackfinlay.CUI.scanner;

/**
 *
 * @author Jack Finlay ID: 1399273
 *
 * @version: 2014.07.30: Created - JF
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
        while(true) {
            
            System.out.print("Enter a command, or type help\n>");
            cui.commandInterpretter(scanner.next());
        }
    }

    

}
