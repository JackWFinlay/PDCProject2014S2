//package pyramid_solitare_jackfinlay.model.ui;
//
//import java.util.Scanner;
//import java.util.StringTokenizer;
//import pyramid_solitare_jackfinlay.model.Card;
//import pyramid_solitare_jackfinlay.model.Game;
//import pyramid_solitare_jackfinlay.model.Help;
//import pyramid_solitare_jackfinlay.model.HighScores;
//import pyramid_solitare_jackfinlay.model.Player;
//
///**
// * Manages input and output to and from the game.
// *
// * @author Jack Finlay ID: 1399273
// */
//public class CUI {
//
//    public static Scanner scanner = new Scanner(System.in);
//    public static Game game;
//
//    /**
//     * The default constructor.
//     */
//    public CUI() {
//    }
//
//    /**
//     * Prints the menu and manages menu related activities.
//     */
//    public void menu() {
//        String input;
//        int selection = 0;
//
//        System.out.println("Welcome to Pyramid Solitaire\n");
//        System.out.println("Menu:");
//        System.out.println("1 - New Game");
//        System.out.println("2 - Highscores");
//        System.out.println("3 - Exit");
//
//        do {
//            boolean validInput = false;
//
//            while (!validInput) { // Check that the input is an integer
//                System.out.print("\nEnter a selection (number) \n > ");
//
//                input = scanner.nextLine();
//
//                try {
//                    selection = Integer.parseInt(input.trim());
//
//                    validInput = true;
//                } catch (NumberFormatException error) {
//                    validInput = false;
//
//                    selection = 4;
//                }
//            }
//
//            if ((selection < 1 || selection > 3)) {
//                System.out.println("Invalid input. Please try again.");
//            }
//
//        } while (selection < 1 || selection > 3);
//
//        if (selection == 1) {
//            newGame();
//
//        } else if (selection == 2) {
//
//            clearConsole();
//            HighScores hs = new HighScores();
//            hs.printHighScores();
//
//        } else if (selection == 3) {
//
//            System.exit(0);
//        }
//    }
//
//    /**
//     * Sets up a new game and runs it.
//     */
//    public void newGame() {
//        String playerName;
//        String input;
//
//        do {
//            System.out.print("Enter your name, or type \"Cancel\" to abort. \n > ");
//            playerName = scanner.nextLine();
//
//            if (playerName.equalsIgnoreCase("Cancel")) { //Player chose to cancel
//                clearConsole();
//                menu();
//            }
//
//            System.out.print("Is \'" + playerName + "\' correct? (Y/N) \n> ");
//
//            input = scanner.nextLine();
//
//            if (!input.equalsIgnoreCase("Y")) {
//                playerName = "";
//            }
//
//        } while (playerName.isEmpty()); // Name cannot be an empty line
//
//        game = new Game(new Player(playerName));
//
//        while (true) {
//
//            System.out.print("Enter a command, or type help\n> ");
//            input = scanner.nextLine();
//            commandInterpretter(input);
//        }
//    }
//
//    public static void clearConsole() {
//        for (int i = 0; i < 50; i++) {
//            System.out.println("");
//        } //Simpler than implementing platform specific console clears.
//    }
//
//    /**
//     * Parses and executes the commands entered by the player.
//     *
//     * @param command The command to be interpreted.
//     */
//    public void commandInterpretter(String command) {
//
//        command = command.toLowerCase();
//
//        if (command.equals("help")) {
//
//            showHelp();
//        } else if (command.equals("exit")) {
//
//            showExit();
//        } else if (command.startsWith("select")) {
//
//            showSelect(command);
//        } else if (command.equals("unselect")) {
//
//            System.out.println("Card unselected.");
//            game.setSelectedCard1(null);
//
//        } else if (command.equals("draw")) {
//
//            game.getBoard().draw();
//            game.continueGame();
//
//        } else if (command.equals("shuffle")) {
//
//            showShuffle();
//        } else {
//            System.out.println("Invalid input. Try again.");
//        }
//    }
//
//    /**
//     * Prompts confirmation to exit and prints out high scores.
//     */
//    private void showExit() {
//        System.out.print("Are you sure? (Y/N)\n> ");
//        String command = scanner.nextLine();
//
//        if (command.equalsIgnoreCase("Y")) {
//            System.out.println("Final Score: "
//                    + game.getPlayer().getScore());
//            System.out.println("");
//            HighScores hs = new HighScores();
//            hs.updateHighScores(game.getPlayer());
//            hs.writeScoreFile();
//            hs.printHighScores();
//
//            menu();
//
//        } else {
//            game.continueGame();
//        }
//    }
//
//    /**
//     * Handles tasks related to the 'Select' command.
//     *
//     * @param command The command string to read the card name from.
//     */
//    private void showSelect(String command) {
//        StringTokenizer tokenizer = new StringTokenizer(command, " ");
//        tokenizer.nextToken();
//
//        if (tokenizer.hasMoreTokens()) { // Check card name is presesnt
//
//            String card = tokenizer.nextToken();
//            Card selectedCard = game.selectCard(card);
//
//            if (selectedCard != null) {
//                game.setSelected(selectedCard);
//            }
//
//        } else {
//            System.out.println("Select command requires a card name.");
//        }
//
//        if (game.getSelectedCard2() != null) {
//            game.checkMatch(game.getSelectedCard1(), game.getSelectedCard2());
//            game.continueGame();
//        }
//    }
//
//    /**
//     * Handles tasks related to the 'Shuffle' command.
//     */
//    private void showShuffle() {
//        if (game.getShufflesRemaining() > 0) {
//
//            game.decrementShufflesRemaining();
//            game.newBoard();
//
//        } else {
//            System.out.println("Cannot reshuffle. Type 'Exit' to end game.");
//        }
//    }
//
//    /**
//     * Handles tasks related to the 'help' command.
//     */
//    private void showHelp() {
//        Help help = new Help();
//
//        System.out.println("Press return key to continue...");
//        String consumeBuffer = scanner.nextLine();
//
//        game.continueGame();
//    }
//}
