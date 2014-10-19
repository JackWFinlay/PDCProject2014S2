package pyramid_solitare_jackfinlay.model;

/**
 * This class represents Player objects.
 *
 * @author Jack Finlay ID: 1399273
 */
public class Player {

    private int score;
    private int boardsCount;
    private String playerName;

    /**
     * Default constructor. Does nothing.
     */
    public Player() {
    }

    /**
     * The preferred constructor. Set up a new player object.
     *
     * @param playerName The player's name.
     */
    public Player( String playerName ) {
        this.playerName = playerName;
        this.score = 0;
        this.boardsCount = 0;
    }

    /**
     * @return The player's current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the player score directly to a value.
     *
     * @param score The value to set player's score to.
     */
    public void setScore( int score ) {
        this.score = score;
    }

    /**
     * Increment the score by the specified amount.
     *
     * @param amount The amount to increase the player's score by.
     */
    public void increaseScore( int amount ) {
        this.score += amount;
        System.out.println("Score increased by " + amount + " points.");
    }

    /**
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player's name to the value passed.
     *
     * @param playerName The string to change playerName to.
     */
    public void setPlayerName( String playerName ) {
        this.playerName = playerName;
    }

    /**
     * @return The number of boards the player has cleared.
     */
    public int getBoardCount() {
        return boardsCount;
    }

    /**
     * Increments the number of boards the player has cleared.
     */
    public void incrementBoardsCount() {
        this.boardsCount++;
    }

}
