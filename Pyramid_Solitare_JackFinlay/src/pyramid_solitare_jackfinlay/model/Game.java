package pyramid_solitare_jackfinlay.model;

import pyramid_solitare_jackfinlay.model.Player;

/**
 * This class runs the logic of the game and initializes components.
 *
 * @author Jack Finlay ID: 1399273
 */
public final class Game {

    public static final int BOARD_CLEAR_SCORE = 20;
    public static final int CARD_MATCH_SCORE = 5;
    public static final int START_SHUFFLE_COUNT = 2;

    private Player player;
    private Board board;
    private Card selectedCard1, selectedCard2;
    private Deck mainDeck;
    private int shufflesRemaining;

    /**
     * The default constructor.
     */
    public Game() {
    }

    /**
     * Constructor to start a new game.
     *
     * @param player The player.
     */
    public Game(Player player) {
        selectedCard1 = null;
        selectedCard2 = null;
        this.player = player;
        this.shufflesRemaining = START_SHUFFLE_COUNT;

        newBoard();
    }

    /**
     * Returns the current player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the current instance of Board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the number of shuffles the player has remaining.
     *
     * @return the shufflesRemaining
     */
    public int getShufflesRemaining() {
        return shufflesRemaining;
    }

    public Card getSelectedCard1() {
        return selectedCard1;
    }

    public void setSelectedCard1(Card selectedCard1) {
        this.selectedCard1 = selectedCard1;
    }

    public Card getSelectedCard2() {
        return selectedCard2;
    }

    public void setSelectedCard2(Card selectedCard2) {
        this.selectedCard2 = selectedCard2;
    }
    
    public Deck getMainDeck(){
        return mainDeck;
    }

    /**
     * Sets up a new game board and decks required.
     */
    public void newBoard() {
        mainDeck = new Deck();
        mainDeck.createDeck();
        this.board = new Board(this, player, mainDeck);
        board.printUI();

    }

    /**
     * Reprints the game board so that the game may continue.
     */
    public void continueGame() {
        

        this.board.printUI();
    }

    /**
     * Finds the location of a card and sets it as selected.
     *
     * @param cardName The name of the card to be selected.
     */
    public Card selectCard(String cardName) {

        Card card = null;
        Deck source = new Deck();

        if (cardName.equalsIgnoreCase(board.getPickUp().getCard(0).getCharacterValue())) {

            card = board.getPickUp().getCard(0);
            source = board.getPickUp();

        } else if (board.getWaste().getSize() > 0
                && cardName.equalsIgnoreCase(board.getWaste().getCard(0).getCharacterValue())) {

            card = board.getWaste().getCard(0);
            source = board.getWaste();

        } else {

            for (Card[] row : board.getBoard()) {
                for (Card aCard : row) {
                    if (cardName.equalsIgnoreCase(aCard.getCharacterValue())) {
                        card = aCard;
                    }
                }
            }
        }

        if (card != null && !card.isPlayable()) {
            System.out.println("Card is not playable at this stage. Try another.");
        } else if (card == null) { //card is null, i.e. Card ID is incorrect.
            System.out.println("Incorrect Card ID. Type help for information.");
        }
        
        return card;
    }

    /**
     * Performs action of setting a card as selected.
     *
     * @param card The card to be set as selected.
     */
    public void setSelected(Card card) {

        if (selectedCard1 == null) {
            selectedCard1 = card;
            System.out.println("Selected card 1:" + card.getSymbolValue());

            if (card.getNumericValue() == 13) { //Card is a King.
                selectedCard2 = new Card();
                // Compare with card of value 0.
            }

        } else {
            selectedCard2 = card;
            System.out.println("Selected card 2:" + card.getSymbolValue()); 
        }

    }
    
    public boolean checkMatch(){
        return checkMatch(selectedCard1,selectedCard2);
    }
    
    /**
     * Checks whether the cards passed to this method are a match.
     *
     * @param card1 A card to compare.
     * @param card2 The other card in the comparison.
     * @return <code>true</code> if cards match to 13,
     *         <code>false</code> if not.
     */
    public boolean checkMatch(Card card1, Card card2) {
        boolean match = false;
        
        if ((card1.getNumericValue() + card2.getNumericValue()) == 13) {

            System.out.println("\nMatch!");
            getPlayer().increaseScore(CARD_MATCH_SCORE);

            card1.getSource().removeCard(card1);
            card2.getSource().removeCard(card2);
            
            card1.setMatched();
            card2.setMatched();
            
            match = true;

        } else {
            System.out.println("\nNot a valid match, try again.");
        }

        selectedCard1 = null;
        selectedCard2 = null;

        if (board.getBoardDeck().getSize() == 0) {
            boardCleared();
        }
        
        return match;
    }

    /**
     * Creates new board and handles scoring.
     */
    private void boardCleared() {

        System.out.println("\n\nBoard Cleared!");

        newBoard();
        getPlayer().increaseScore(BOARD_CLEAR_SCORE);
        getPlayer().incrementBoardsCount();

    }

    /**
     * Decrements the number of shuffles remaining.
     *
     * @return <code>true</code> if successful, <code>false</code> if not.
     */
    public boolean decrementShufflesRemaining() {
        boolean success = false;

        if (shufflesRemaining > 0) {
            success = true;
            this.shufflesRemaining--;
        }

        return success;
    }

}
