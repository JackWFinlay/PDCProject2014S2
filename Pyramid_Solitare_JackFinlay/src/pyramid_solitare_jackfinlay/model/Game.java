package pyramid_solitare_jackfinlay.model;

import java.util.Observable;

/**
 * This class runs the logic of the game and initializes components.
 *
 * @author Jack Finlay ID: 1399273
 */
public final class Game extends Observable {

    public static final int BOARD_CLEAR_SCORE = 20;
    public static final int CARD_MATCH_SCORE = 5;
    public static final int START_SHUFFLE_COUNT = 2;
    public HighScores highScores;

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
    public Game( Player player ) {
        selectedCard1 = null;
        selectedCard2 = null;
        this.player = player;
        shufflesRemaining = START_SHUFFLE_COUNT;
        mainDeck = new Deck();
        mainDeck.createDeck();
        highScores = new HighScores();

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

    public void setPlayer( Player player ) {
        this.player = player;
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

    public void setSelectedCard1( Card selectedCard1 ) {
        this.selectedCard1 = selectedCard1;
    }

    public Card getSelectedCard2() {
        return selectedCard2;
    }

    public void setSelectedCard2( Card selectedCard2 ) {
        this.selectedCard2 = selectedCard2;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    /**
     * Sets up a new game board and decks required.
     */
    public void newBoard() {
        Deck deck = Deck.copy(mainDeck);
        this.board = new Board(this, player, deck);
        board.printUI();

    }

    /**
     * Reprints the game board so that the game may continue.
     */
    public void continueGame() {
        board.printUI();
    }

    /**
     * Finds the location of a card and sets it as selected.
     *
     * @param cardName The name of the card to be selected.
     * @return The card that was selected.
     */
    public Card selectCard( Card cardName ) {

        Card card = null;
        Deck source = new Deck();

        if ( cardName.equals(board.getPickUp().getCard(0)) ) {

            card = board.getPickUp().getCard(0);
            source = board.getPickUp();

        } else if ( board.getWaste().getSize() > 0
                && cardName.equals(board.getWaste().getCard(0)) ) {

            card = board.getWaste().getCard(0);
            source = board.getWaste();

        } else {

            for ( Card[] row : board.getBoard() ) {
                for ( Card aCard : row ) {
                    if ( cardName.equals(aCard) ) {
                        card = aCard;
                    }
                }
            }
        }

        if ( card != null && (!card.isPlayable() || card.isMatched()) ) {
            System.out.println("Card is not playable at this stage. Try another.");
            card = null;
        } else if ( card == null ) { //card is null, i.e. Card ID is incorrect.
            System.out.println("Incorrect Card ID. Type help for information.");
        }

        return card;
    }

    /**
     * Performs action of setting a card as selected.
     *
     * @param card The card to be set as selected.
     */
    public void setSelected( Card card ) {

        if ( selectedCard1 == null ) {
            selectedCard1 = card;
            System.out.println("Selected card 1:" + card.getSymbolValue());

            if ( card.getNumericValue() == 13 ) { //Card is a King.
                selectedCard2 = new Card();
                // Compare with card of value 0.
            }

        } else {
            selectedCard2 = card;
            System.out.println("Selected card 2:" + card.getSymbolValue());
        }

    }

    /**
     * Checks whether the cards passed to this method are a match.
     *
     * @param card1 A card to compare.
     * @param card2 The other card in the comparison.
     * @return <code>true</code> if cards match to 13, <code>false</code> if
     * not.
     */
    public boolean checkMatch( Card card1, Card card2 ) {
        boolean match = false;

        if ( (card1.getNumericValue() + card2.getNumericValue()) == 13 ) {

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

        if ( board.getBoard()[0][0].isMatched() ) {
            // The last card in the board was cleared.
            boardCleared();
        }

        return match;
    }

    /**
     * Creates new board and handles scoring.
     */
    private void boardCleared() {

        System.out.println("\n\nBoard Cleared!");

        getPlayer().increaseScore(BOARD_CLEAR_SCORE);
        getPlayer().incrementBoardsCount();
        newBoard();

    }

    /**
     * Decrements the number of shuffles remaining.
     *
     * @return <code>true</code> if successful, <code>false</code> if not.
     */
    public boolean decrementShufflesRemaining() {
        boolean success = false;

        if ( shufflesRemaining > 0 ) {
            success = true;
            this.shufflesRemaining--;
        }

        return success;
    }

    public void shuffle() {
        this.selectedCard1 = null;
        decrementShufflesRemaining();
        newBoard();
    }

    /**
     * Overrides the superclass implementation so that setChanged is called
     * implicitly.
     */
    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }
}
