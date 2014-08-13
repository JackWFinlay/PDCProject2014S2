/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_solitare_jackfinlay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pyramid_solitare_jackfinlay.CUI.scanner;
import static pyramid_solitare_jackfinlay.Main.cui;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public final class HighScores {

    ArrayList<Score> highScores;

    public HighScores() {
        highScores = new ArrayList();
        readScoreFile();

    }

    public void readScoreFile() {

        try {

            URL url = getClass().getResource("Scores.txt");
            BufferedReader reader = new BufferedReader(new FileReader(url.getPath()));
            String line;

            while ((line = reader.readLine()) != null) {

                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                int rank = Integer.parseInt(tokenizer.nextToken());
                int score = Integer.parseInt(tokenizer.nextToken());
                String name = (tokenizer.nextToken());

                highScores.add(new Score(rank, name, score));

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printHighScores() {
        
        System.out.println("Rank   Name        Score");
        
        for (Score score : highScores) {

            System.out.print(score.getRank());
            System.out.print("      " + score.getPlayerName());
            System.out.print("      " + score.getScore());
            System.out.println("");

        }

        System.out.println("Type anything to return...");

        scanner.next();
        CUI.clearConsole();
        cui.menu();
    }

}
