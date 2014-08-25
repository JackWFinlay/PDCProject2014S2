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
    private Deck mainDeck, source1, source2;
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

    /**
     * Decrements the number of shuffles remaining.
     */
    public void decrementShufflesRemaining() {
        this.shufflesRemaining--;
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

    public Deck getSource1() {
        return source1;
    }

    public void setSource1(Deck source1) {
        this.source1 = source1;
    }

    public Deck getSource2() {
        return source2;
    }

    public void setSource2(Deck source2) {
        this.source2 = source2;
    }

    /**
     * Sets up a new game board and decks required.
     */
    public void newBoard() {
        mainDeck = new Deck();
        mainDeck.createDeck();
        board = new Board(this, player, mainDeck);
        getBoard().printUI();

    }

    /**
     * Reprints the game board so that the game may continue.
     */
    public void continueGame() {
        getBoard().printUI();
    }

    /**
     * Finds the location of a card and sets it as selected.
     *
     * @param cardName The name of the card to be selected.
     */
    public void selectCard(String cardName) {

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

        if (card != null && card.isPlayable()) {
            setSelected(card, source);
        } else if (card != null && !card.isPlayable()) {
            System.out.println("Card is not playable at this stage. Try another.");
        } else { //card is null, i.e. Card ID is incorrect.
            System.out.println("Incorrect Card ID. Type help for information.");
        }

    }

    /**
     * Performs action of setting a card as selected.
     *
     * @param card The card to be set as selected.
     * @param source The deck that that <i>card</i> came from.
     */
    public void setSelected(Card card, Deck source) {

        if (selectedCard1 == null) {
            selectedCard1 = card;
            source1 = source;
            System.out.println("Selected card 1:" + card.getSymbolValue());

            if (card.getNumericValue() == 13) { //Card is a King.
                checkMatch(selectedCard1, new Card());
                // Compare with card of value 0.
                continueGame();
            }

        } else {
            selectedCard2 = card;
            source2 = source;
            System.out.println("Selected card 2:" + card.getSymbolValue());
            checkMatch(selectedCard1, selectedCard2);
            continueGame();
        }

    }

    /**
     * Checks whether the cards passed to this method are a match.
     *
     * @param card1 A card to compare.
     * @param card2 The other card in the comparison.
     */
    public void checkMatch(Card card1, Card card2) {
        if ((card1.getNumericValue() + card2.getNumericValue()) == 13) {

            card1.setMatched();
            card2.setMatched();

            System.out.println("\nMatch!");
            getPlayer().increaseScore(CARD_MATCH_SCORE);

            if (source1 != null) {
                source1.removeCard(card1);
            }
            if (source2 != null) {
                source2.removeCard(card2);

            }

        } else {
            System.out.println("\nNot a valid match, try again.");
        }

        selectedCard1 = null;
        selectedCard2 = null;

        if (board.getBoardDeck().getSize() == 0) {
            boardCleared();
        }
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

}
