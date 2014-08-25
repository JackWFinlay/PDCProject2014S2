package pyramid_solitare_jackfinlay.model;

/**
 * Represents a card object in the game.
 *
 * @author Jack Finlay ID: 1399273
 *
 * @version: 2014.07.30: Created - JF
 * @version: 2014.08.05: Updated to add symbolValue field and methods - JF
 * @version: 2014.08.22: Added setMatched() - JF
 */
public class Card {

    private String symbolValue;
    private String characterValue;

    private int numericValue;

    private boolean playable = false;
    private boolean matched = false;

    /**
     * Default constructor. Creates cards with no value.
     */
    public Card() {
        this.numericValue = 0; //Empty card, basically.
    }

    /**
     * Constructor for building valid cards.
     *
     * @param suit The suit of the card.
     * @param numericValue The value of the card.
     */
    public Card(CardSuits suit, int numericValue) {
        this.numericValue = numericValue;
        this.symbolValue = suit.getSymbol();
        this.characterValue = suit.toString();

        String value = "";
        if (numericValue > 10 || numericValue == 1) {
            if (numericValue == 1) {
                value = "A";

            } else if (numericValue == 11) {
                value = "J";
            } else if (numericValue == 12) {
                value = "Q";
            } else if (numericValue == 13) {
                value = "K";
            }

            this.symbolValue += value;
            this.characterValue += value;

        } else {
            this.symbolValue += (String.valueOf(numericValue) + "");
            this.characterValue += (String.valueOf(numericValue) + "");
        }
    }

    /**
     * Returns the symbol value of a card for display.
     *
     * @return The symbol value of a card.
     */
    public String getSymbolValue() {
        return this.symbolValue;
    }

    /**
     * Returns the character value of a card.
     *
     * @return The character value of the card.
     */
    public String getCharacterValue() {
        return this.characterValue;
    }

    /**
     * Returns the numerical value of the card.
     *
     * @return The numeric value of the card.
     */
    public int getNumericValue() {
        return this.numericValue;
    }

    /**
     * Returns whether a card is playable.
     *
     * @return <code>true</code> if card is playable, <code>false</code> if not.
     */
    public boolean isPlayable() {
        return this.playable;
    }

    /**
     * Returns whether a card has been matched.
     *
     * @return <code>true</code> if card has been matched, <code>false</code> if
     * not.
     */
    public boolean isMatched() {
        return this.matched;
    }

    /**
     * Sets card playable field to specified value.
     *
     * @param playable The state for the <i>playable</i> field to be set to.
     */
    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    /**
     * Sets a card as matched.
     */
    public void setMatched() {
        this.symbolValue = "xx";
        this.numericValue = 0;
        this.matched = true;
        this.playable = false;

    }

}
