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
 */
public class CUI {

    public static Scanner scanner = new Scanner(System.in);

    public void menu() {
        int selection = 0;

        System.out.println("Welcome to Pyramid Solitaire\n");
        System.out.println("Menu:");
        System.out.println("1 - New Game");
        System.out.println("2 - Highscores");
        System.out.println("3 - Exit");

        System.out.print("\nEnter a selection. \n > ");

        while (selection < 1 || selection > 3) {

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
            } else {
                System.out.print("Invalid input. Please try again. \n > ");
                scanner.nextLine(); // Clear buffer
            }

        }

        if (selection == 1) {
            newGame();

        } else if (selection == 2) {
            
            clearConsole();
            HighScores hs = new HighScores();
            hs.printHighScores();
            
        } else if (selection == 3) {
            
            System.exit(0);
        }
    }

    public void newGame() {
        String playerName;
        do {
            scanner.nextLine(); // Clears buffer.
            System.out.print("Enter your name, or type \"Cancel\" to abort. \n > ");
            playerName = scanner.nextLine();
        } while (playerName.isEmpty());

        if (playerName.equalsIgnoreCase("Cancel")) { //Player chose to cancel
            clearConsole();
            menu();
        } else {
            Game game = new Game(new Player(playerName));
        }
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }

//        String os = System.getProperty("os.name");
//        String command;
//        if (os.startsWith("Window")) {
//            command = "cls";
//        } else {
//            command = "clear";
//        }
//
//        try {
//            Runtime.getRuntime().exec(command);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
    }
}