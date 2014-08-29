package pyramid_solitare_jackfinlay.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pyramid_solitare_jackfinlay.Main.cui;
import pyramid_solitare_jackfinlay.model.ui.CUI;
import static pyramid_solitare_jackfinlay.model.ui.CUI.scanner;

/**
 * Reads, maintains, and writes the saved high scores file.
 *
 * @author Jack Finlay ID: 1399273
 */
public final class HighScores {

    private static final String SCORES_FILE_LOCATION = "../Data/Scores.txt";
    private ArrayList<Score> highScores;

    /**
     * Constructor to set up HighScores instance.
     */
    public HighScores() {
        highScores = new ArrayList();
        readScoreFile();
    }

    /**
     * Reads the score file stored in the .jar file.
     */
    private void readScoreFile() {

        try {

            InputStream in = getClass().getResourceAsStream(SCORES_FILE_LOCATION);
            // Using InputStream rather than FileReader allows storing of 
            // txt file in the .jar file
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;

            while ((line = reader.readLine()) != null) {

                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                int rank = Integer.parseInt(tokenizer.nextToken());
                int score = Integer.parseInt(tokenizer.nextToken());
                String name = (tokenizer.nextToken());

                highScores.add(new Score(rank, name, score));

            }

            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates the current score roster.
     *
     * @param player The current player.
     */
    public void updateHighScores(Player player) {
        int lowestIndex = highScores.size();
        Score currentPlayer;
        currentPlayer = new Score(0, player.getPlayerName(), player.getScore());

        for (int i = (highScores.size() - 1); i >= 0; i--) {
            if (currentPlayer.getScore() > highScores.get(i).getScore()) {
                lowestIndex = i;
            }
        } // Find if player beat a high score.

        if (highScores.size() >= 10 || lowestIndex <= highScores.size()) {
            highScores.add(lowestIndex, currentPlayer);
            // Add player at index - index of 10 puts it at rank 11.
        } else {
            highScores.add(currentPlayer);
            // If there aren't 10 highscores and it doesn't beat any, 
            // stick on end of list.
        }

        for (Score score : highScores) {
            score.setRank((highScores.indexOf(score)) + 1);
        } // Resest ranks

        if (highScores.size() > 10) {
            highScores.remove(10); // Remove score at rank 11.
        }
    }

    /**
     * Writes the current high score list to a .txt file.
     */
    public void writeScoreFile() {
        PrintWriter output = null;

        URL url = getClass().getResource(SCORES_FILE_LOCATION);

        try {
            output = new PrintWriter(new FileOutputStream(url.getPath()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (output != null) {

            for (Score score : highScores) {
                int rank = score.getRank();
                int playerScore = score.getScore();
                String name = score.getPlayerName();

                String outputLine = (rank + "," + playerScore + "," + name);
                output.println(outputLine);
            }

            output.close();
        }
    }

    /**
     * Prints the current high score list to the console.
     */
    public void printHighScores() {

        System.out.println("Rank   Name        Score");

        for (Score score : highScores) {

            System.out.print(score.getRank());
            System.out.print("      " + score.getPlayerName());
            System.out.print("      " + score.getScore());
            System.out.println("");

        }

        System.out.println("Press the enter key to return to menu...");
        scanner.nextLine(); // Clear scanner buffer.

        CUI.clearConsole();
        cui.menu();
    }

}
