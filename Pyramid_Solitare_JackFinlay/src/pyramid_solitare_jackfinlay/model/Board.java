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

    public Board() {
    }

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
     * @return the pickUp
     */
    public Deck getPickUp() {
        return pickUp;
    }

    /**
     * @param pickUp the pickUp to set
     */
    public void setPickUp(Deck pickUp) {
        this.pickUp = pickUp;
    }

    /**
     * @return the waste
     */
    public Deck getWaste() {
        return waste;
    }

    /**
     * @param waste the waste to set
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

    private void populatePickUpPile(Deck deck) {
        while (deck.getSize() > 0) {
            Card card = deck.getCard(0);
            card.setPlayable(false);
            pickUp.addCard(card);
            deck.removeCard(card);
            card.setSource(pickUp);
        }

        pickUp.getCard(0).setPlayable(true);
    }

    public void draw() {

        if (pickUp.getSize() == 1) {

            populatePickUpPile(getWaste());

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

        game.setSelectedCard1(null);
        game.setSelectedCard2(null);

    }

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
        System.out.println("Pickup: " + pickUp.getCard(0).getSymbolValue());

        if (getWaste() != null && waste.getSize() > 0) {
            waste.getCard(0).setPlayable(true);
            System.out.println("Waste: " + waste.getCard(0).getSymbolValue());
        }
    }

    private void setPlayableCardsOnBoard() {

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

    public Deck getBoardDeck() {
        return boardDeck;
    }

}
