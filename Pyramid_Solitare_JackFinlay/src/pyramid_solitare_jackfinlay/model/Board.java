package pyramid_solitare_jackfinlay.model;

/**
 * Creates and maintains the game board.
 *
 * @author Jack Finlay ID: 1399273
 */
public class Board {

    private Deck deck, boardDeck, pickUp, waste;
    private Card[][] board;
    private Game game;
    private Player player;

    /**
     * The default constructor of this class.
     */
    public Board() {
    }

    /**
     * The preferred constructor for this class. Sets up the game decks and game
     * board.
     *
     * @param game The current instance of Game
     * @param player The current player.
     * @param deck A deck to populate the game board with.
     */
    public Board(Game game, Player player, Deck deck) {

        this.game = game;
        this.player = player;
        this.deck = deck;
        this.board = createBoard();
        this.boardDeck = new Deck();
        this.pickUp = new Deck();
        this.waste = new Deck();

        Deck.shuffleDeck(this.deck);
        populateBoard(deck);
        populatePickUpPile(deck);

    }

    // <editor-fold desc="Setters and Getters">
    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * @return the pickUp deck
     */
    public Deck getPickUp() {
        return pickUp;
    }

    /**
     * @param pickUp The deck to set pickup to.
     */
    public void setPickUp(Deck pickUp) {
        this.pickUp = pickUp;
    }

    /**
     * @return the waste deck.
     */
    public Deck getWaste() {
        return waste;
    }

    /**
     * @param waste the deck to set waste to.
     */
    public void setWaste(Deck waste) {
        this.waste = waste;
    }

    /**
     * @return the board
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Card[][] board) {
        this.board = board;
    }

    // </editor-fold>
    /**
     * Sets up a 2D array to store the cards in the pyramid arrangement.
     *
     * @return An array representing the card pyramid.
     */
    private Card[][] createBoard() {
        Card[][] newBoard = new Card[7][];

        newBoard[0] = new Card[1];
        newBoard[1] = new Card[2];
        newBoard[2] = new Card[3];
        newBoard[3] = new Card[4];
        newBoard[4] = new Card[5];
        newBoard[5] = new Card[6];
        newBoard[6] = new Card[7];

        return newBoard;
    }

    /**
     * Fills the pyramid array with cards.
     *
     * @param deck The deck to populate the board with.
     */
    private void populateBoard(Deck deck) {

        for (Card[] row : getBoard()) {
            for (int i = 0; i < row.length; i++) {
                Card card = deck.getCard(0);
                row[i] = card;
                deck.removeCard(card);
                boardDeck.addCard(card);
            }
        }

        for (Card card : getBoard()[6]) {
            card.setPlayable(true);
        }
    }

    /**
     * Populates the pick-up pile with the remaining cards of deck.
     */
    private void populatePickUpPile(Deck deck) {
        while (deck.getSize() > 0) {
            Card card = deck.getCard(0);
            card.setPlayable(false);
            pickUp.addCard(card);
            deck.removeCard(card);
            card.setSource(pickUp);
        }

        if (pickUp.getSize() >= 0) {
            pickUp.getCard(0).setPlayable(true);
        }
    }

    /**
     * Moves a card from the top of the pick-up pile to the top of the waste
     * pile.
     */
    public void draw() {

        if (pickUp.getSize() <= 1) {
            // Moves the waste pile to the pick-up pile when pick-up is empty.
            if (waste.getSize() > 0) {
                populatePickUpPile(waste);
            }
        } else {

            Card card = pickUp.getCard(0);

            waste.addCard(card);

            pickUp.removeCard(card);
            pickUp.getCard(0).setPlayable(true);

            card.setSource(waste);

            if (waste.getSize() > 1) {
                waste.getCard(1).setPlayable(false);
                // Set the card that was previously on top as unplayable.
            }

        }

        // Clears the currently selected cards as only the top cards can be used.
        game.setSelectedCard1(null);
        game.setSelectedCard2(null);

    }

    /**
     * Sets up and prints the game board.
     */
    public void printUI() {

        System.out.println("Player: " + player.getPlayerName()
                + " | Shuffles remaining: " + game.getShufflesRemaining());
        System.out.println("Score: " + player.getScore()
                + " | Boards Complete: " + player.getBoardCount());
        System.out.println("");
        printBoard();
        setPlayableCardsOnBoard();

    }

    private void printBoard() {

        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < 7 - i; j++) {
                System.out.print("  ");
                //Forward spacing between cards
            }
            for (Card card : getBoard()[i]) {

                System.out.print(card.getSymbolValue() + " ");

                if (card.getNumericValue() != 10) {
                    System.out.print(" ");
                    // Cards with one digit/character values need extra spacing.
                }
            }
            System.out.println();
        }

        System.out.println();

        if (pickUp.getSize() == 0 && waste != null) {
            populatePickUpPile(waste);
        }
        System.out.println("Pickup: " + pickUp.getCard(0).getSymbolValue());

        if (getWaste() != null && waste.getSize() > 0) {
            waste.getCard(0).setPlayable(true);
            System.out.println("Waste: " + waste.getCard(0).getSymbolValue());
        }
    }

    /**
     * Modifies card's playable field, tracking what is playable after each
     * move.
     */
    public void setPlayableCardsOnBoard() {

        for (int i = 0; i < 6; i++) { // Check every row except row 6.
            for (int j = 0; j < getBoard()[i].length; j++) {
                Card card = getBoard()[i][j];

                if (getBoard()[i + 1][j].isMatched()
                        && getBoard()[i + 1][j + 1].isMatched()) {

                    card.setPlayable(true);
                } // Checks whether the cards below have been removed.
            }
        }

        pickUp.getCard(0).setPlayable(true);

        if (getWaste() != null && waste.getSize() > 0) {
            waste.getCard(0).setPlayable(true);
        }
    }

}
