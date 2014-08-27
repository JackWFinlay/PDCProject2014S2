package pyramid_solitare_jackfinlay.model;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class Score extends Player{
    private int rank;
    
    public Score(){
        super();
    }
    
    public Score( int rank, String playerName, int score  ){
        super(playerName);
        setScore(score);
        this.rank = rank;
    }
    
    public int getRank(){
        return rank;
    }
    
    public void setRank(int rank){
        this.rank = rank;
    }
}
