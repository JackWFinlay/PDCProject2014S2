package pyramid_solitare_jackfinlay.model;

/**
 * This class represents score objects, for storing high scores.
 *
 * @author Jack Finlay ID: 1399273
 */
public class Score extends Player {

    private int rank;
    
    /**
     * Default constructor. Doesn't actually do anything.
     */
    public Score() {
        super();
    }

    /**
     * Constructor to set up a new score object.
     * 
     * @param rank The current rank associated with the score.
     * @param playerName The name of the player.
     * @param score The player's score.
     */
    public Score(int rank, String playerName, int score) {
        super(playerName);
        setScore(score);
        this.rank = rank;
    }

    /** 
     * @return The high score ranking associated with this score.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the high score ranking of this score object.
     * @param rank The rank to set this score object to.
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
}
