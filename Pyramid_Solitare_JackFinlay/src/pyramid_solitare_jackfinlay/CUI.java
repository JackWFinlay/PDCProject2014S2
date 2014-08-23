/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class CUI {

    public static Scanner scanner = new Scanner(System.in);
    public static Game game;

    public void menu() {
        int selection = 0;

        System.out.println("Welcome to Pyramid Solitaire\n");
        System.out.println("Menu:");
        System.out.println("1 - New Game");
        System.out.println("2 - Highscores");
        System.out.println("3 - Exit");

        System.out.print("\nEnter a selection. \n > ");

        do {

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
            } else {
                selection = 4;
            }

            if ((selection < 1 || selection > 3)) {
                System.out.print("Invalid input. Please try again. \n > ");
                scanner.nextLine(); // Clear buffer
            }

        } while (selection < 1 || selection > 3);

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
            game = new Game(new Player(playerName));
        }
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public void commandInterpretter(String command) {
        
        command = command.toLowerCase();
        
        
        if (command.equals("help")) {

            showHelp();
        } else if (command.equals("exit")) {

            System.out.print("Are you sure? (Y/N)\n>");
            command = scanner.next();
            if (command.equalsIgnoreCase("Y")) {
                System.exit(0);
            } else {
                game.continueGame();
            }
        } else if (command.startsWith("select")) {
            
            StringTokenizer tokenizer = new StringTokenizer(command," ,");
            tokenizer.nextToken();
            
            
            if (tokenizer.hasMoreTokens()) { // Check card name is presesnt
                String card = tokenizer.nextToken();
                game.selectCard(card); 
            
            } else {
                System.out.println("Select command requires a card name.");
            }
        } else if (command.equalsIgnoreCase("draw")) {
            Game.board.draw();
            game.continueGame();
        } else {
            System.out.println("Invalid input. Try again.");
        }
    }

    private void showHelp() {
        System.out.println("Placeholder help screen.");
    }
}
