package pyramid_solitare_jackfinlay.model;

/**
 * Enumerates the names, character and symbol representations of the card suits.
 *
 * @author Jack Finlay ID: 1399273
 */
public enum CardSuits {

    CLUBS("C", "♣"), SPADES("S", "♠"), DIAMONDS("D", "♦"), HEARTS("H", "♥");

    private final String suit;
    private final String symbol;

    private CardSuits(String suit, String symbol) {
        this.suit = suit;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return suit;
    }

    public String getSymbol() {
        return symbol;
    }
}
