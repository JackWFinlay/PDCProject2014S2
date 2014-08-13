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
public class Score extends Player{
    private final int rank;
    
    public Score( int rank, String playerName, int score  ){
        super(playerName);
        setScore(score);
        this.rank = rank;
    }
    
    public int getRank(){
        return rank;
    }
}
