package pyramid_solitare_jackfinlay.model;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class Player {
    private int score;
    private int boardsCount;
    private String playerName;
    
    public Player(){}
    
    public Player( String playerName ){
        this.playerName = playerName;
        this.score = 0;
        this.boardsCount = 0;
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

    public int getBoardCount() {
        return boardsCount;
    }
    
    public void incrementBoardsCount(){
        this.boardsCount++;
    }
    
    
}
