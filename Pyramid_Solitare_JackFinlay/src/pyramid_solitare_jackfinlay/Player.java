/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid_solitare_jackfinlay;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class Player {
    private int score;
    private int boards;
    private String playerName;
    
    public Player(){}
    
    public Player( String playerName ){
        this.playerName = playerName;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void increaseScore(int amount){
        this.score += amount;
        System.out.println("Score increased by " + amount + " points.");
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getBoards() {
        return boards;
    }

    public void setBoards(int boards) {
        this.boards = boards;
    }
    
    
}
