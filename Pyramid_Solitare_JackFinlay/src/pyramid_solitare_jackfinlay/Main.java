/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import java.util.Scanner;

/**
 *
 * @author Jack Finlay ID: 1399273
 *
 * @version: 2014.07.30: Created - JF
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * The Main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        int selection;

        System.out.println("Welcome to Pyramid Solitaire\n\n");
        System.out.println("Menu:");
        System.out.println("1 - New Game");
        System.out.println("2 - Highscores");
        System.out.println("3 - Exit");

        do {
            System.out.print("\nEnter a selection: ");
            selection = scanner.nextInt();
        } while (selection < 1 || selection > 3);

        if (selection == 1) {
            String playerName;
            do {
                scanner.nextLine(); // Clears buffer.
                System.out.print("Please enter your name: ");
                playerName = scanner.nextLine();
            } while (playerName.isEmpty());

            Game game = new Game(playerName);
            
        } else if (selection == 2) {
            //TODO: HighScore code
        } else if (selection == 3) {

            //TODO: Exit Code
        }
    }

}
