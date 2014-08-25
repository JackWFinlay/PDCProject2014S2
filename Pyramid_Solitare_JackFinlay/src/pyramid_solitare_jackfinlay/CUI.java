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
        String input;
        int selection = 0;

        System.out.println("Welcome to Pyramid Solitaire\n");
        System.out.println("Menu:");
        System.out.println("1 - New Game");
        System.out.println("2 - Highscores");
        System.out.println("3 - Exit");

        do {
            boolean validInput = false;

            while (!validInput) { // Check that the input is an integer
                System.out.print("\nEnter a selection. \n > ");

                input = scanner.nextLine();

                try {
                    selection = Integer.parseInt(input.trim());

                    validInput = true;
                } catch (NumberFormatException error) {
                    validInput = false;

                    selection = 4;
                }
            }

            if ((selection < 1 || selection > 3)) {
                System.out.println("Invalid input. Please try again.");
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
        String input;

        do {
            do {
                System.out.print("Enter your name, or type \"Cancel\" to abort. \n > ");
                playerName = scanner.nextLine();

            } while (playerName.isEmpty()); // Name cannot be an empty line

            System.out.print("Is \'" + playerName + "\' correct? (Y/N) \n> ");

            input = scanner.nextLine();

        } while (!input.equalsIgnoreCase("Y"));//Anything other than "Y"

        if (playerName.equalsIgnoreCase("Cancel")) { //Player chose to cancel
            clearConsole();
            menu();
        } else {
            game = new Game(new Player(playerName));
        }

        while (true) {

            System.out.print("Enter a command, or type help\n> ");
            input = scanner.nextLine();
            commandInterpretter(input);
        }
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        } //Simpler than implementing platform specific console clears.
    }

    public void commandInterpretter(String command) {

        command = command.toLowerCase();

        if (command.equals("help")) {

            showHelp();
        } else if (command.equals("exit")) {

            System.out.print("Are you sure? (Y/N)\n> ");
            command = scanner.next();
            if (command.equalsIgnoreCase("Y")) {
                System.out.println("Final Score: "
                        + game.getPlayer().getScore());

                System.exit(0);

            } else {

                game.continueGame();
            }

        } else if (command.startsWith("select")) {

            StringTokenizer tokenizer = new StringTokenizer(command, " ");
            tokenizer.nextToken();

            if (tokenizer.hasMoreTokens()) { // Check card name is presesnt

                String card = tokenizer.nextToken();
                game.selectCard(card);

            } else {
                System.out.println("Select command requires a card name.");
            }
        } else if (command.equals("unselect")) {
            System.out.println("Card unselected.");
            game.setSelectedCard1(null);
            game.setSource1(null);
        } else if (command.equals("draw")) {
            game.getBoard().draw();
            game.continueGame();
        } else if (command.equals("shuffle")) {

            if (game.getShufflesRemaining() > 0) {

                game.decrementShufflesRemaining();
                game.newBoard();

            } else {
                System.out.println("Cannot reshuffle. Type exit to end game.");
            }

        } else {
            System.out.println("Invalid input. Try again.");
        }
    }

    private void showHelp() {
        System.out.println("Placeholder help screen.");
    }
}
