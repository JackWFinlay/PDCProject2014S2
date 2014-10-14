package pyramid_solitare_jackfinlay.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads, maintains, and writes the saved high scores file.
 *
 * @author Jack Finlay ID: 1399273
 */
public final class HighScores implements Runnable {

    private static final String SCORES_FILE_LOCATION = "../Data/Scores.txt";
    private static ArrayList<Score> highScores;

    public static Connection connection;
    public static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String URL = "jdbc:derby:PyramidSolitaireDB;create=true";

    /**
     * Constructor to set up HighScores instance.
     */
    public HighScores() {
        highScores = new ArrayList();

        // Performs actions as a seperate thread to the rest of the game as the
        // results are not required in order to play a game.
        new Thread(this).start();

    }

    public void run() {
        establishConnection();

        // Read scores from table.
        readScores(getScoreTable());

        printHighScores();
    }

    public void establishConnection() {
        ResultSet results = null;
        try {
            // Load Driver
            Class.forName(DRIVER);
            //System.out.println("Driver Loaded.");

            // Establish connection
            connection = DriverManager.getConnection(URL);
            System.out.println(URL + " connected...");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Reads the scores from the database.
     */
    private void readScores(ResultSet rs) {

        if (rs != null) {
            try {

                while (rs.next()) {

                    int rank = rs.getInt("RANK");
                    int score = rs.getInt("SCORE");
                    String name = rs.getString("NAME");

                    highScores.add(new Score(rank, name, score));

                }

                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet getScoreTable() {
        ResultSet results = null;

        try {

            // Check if tables exist
            DatabaseMetaData metadata = connection.getMetaData();
            results = metadata.getTables(null, null, "SCORES", null);

            // Create SCORES table if it doesn't exist.
            if (!results.next()) {
                createScoreTable();

            }

            Statement statement = connection.createStatement();
            results = statement.executeQuery("SELECT * FROM SCORES");

        } catch (SQLException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }

    public void createScoreTable() {
        try {
            Statement statement = connection.createStatement();

            //Create the table:
            statement.execute("CREATE TABLE SCORES(RANK INT, NAME VARCHAR(20), SCORE INT)");

            System.out.println("Score Table created.");
        } catch (SQLException ex) {
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

        writeScoreFile();
    }

    /**
     * Writes the current high score list to the database.
     */
    public void writeScoreFile() {
        try {
            Statement statement = connection.createStatement();
            clearHighScores();

            for (Score score : highScores) {
                int rank = score.getRank();
                int playerScore = score.getScore();
                String name = score.getPlayerName();

                String query = ("INSERT INTO SCORES VALUES(" + rank + ", '" + name + "', " + playerScore + ")");
                statement.execute(query);
            }

        } catch (SQLException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Prints the current high score list to the console.
     */
    public static String printHighScores() {

        String scores = "";
        scores += ("Rank  Score    Name \n\n");

        for (Score score : highScores) {

            scores += String.format("%2d", score.getRank());
            scores += ("  ");
            scores += String.format("%5d", score.getScore());
            scores += ("      ");
            scores += (score.getPlayerName());
            scores += ("\n");

        }

        System.out.println(scores);
        return scores;
    }
    
    public static void clearHighScores() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM SCORES");
        } catch (SQLException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void resetScores() {
        clearHighScores();
        highScores.removeAll(highScores);
    }

}
