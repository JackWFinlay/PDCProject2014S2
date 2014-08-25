package pyramid_solitare_jackfinlay.model;

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
