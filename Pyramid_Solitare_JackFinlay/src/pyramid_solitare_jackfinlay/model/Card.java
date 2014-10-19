package pyramid_solitare_jackfinlay.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import pyramid_solitare_jackfinlay.Main;

/**
 * Represents a card object in the game.
 *
 * @author Jack Finlay ID: 1399273
 *
 */
public class Card implements Cloneable {

    private String symbolValue;
    private String characterValue;

    private int numericValue;

    private Deck sourceDeck;

    private boolean playable = false;
    private boolean matched = false;

    private ImageIcon cardImage;

    /**
     * Default constructor. Creates cards with no value.
     */
    public Card() {
        this.numericValue = 0; //Empty card, basically.
        this.sourceDeck = new Deck();
    }

    /**
     * Constructor for building valid cards.
     *
     * @param suit The suit of the card.
     * @param numericValue The value of the card.
     * @param source The source deck of this card.
     */
    public Card(CardSuits suit, int numericValue, Deck source) {
        this.numericValue = numericValue;
        this.symbolValue = suit.getSymbol();
        this.characterValue = suit.toString();
        this.sourceDeck = source;

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

        try {
            String imagePath = ("Images/" + this.characterValue + ".png");
            BufferedImage image = ImageIO.read(Main.class.getResourceAsStream(imagePath));
            Image resizedImage = image.getScaledInstance(48, 70, java.awt.Image.SCALE_SMOOTH);
            this.cardImage = new ImageIcon(resizedImage);

        } catch (Exception e) {
            System.out.println("Something broke re: getting card images in card class.");
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
     * @return The source deck of this card.
     */
    public Deck getSource() {
        return this.sourceDeck;
    }

    /**
     * Sets the source deck of this card.
     *
     * @param source The deck to set this card's source as.
     */
    public void setSource(Deck source) {
        this.sourceDeck = source;
    }

    public ImageIcon getCardImage() {
        return this.cardImage;
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
        this.sourceDeck = null;

    }

    /**
     * Allows a card to be cloned so that a deep copy can be made of a deck.
     * @return A copy of the card.
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Card clone() throws CloneNotSupportedException {
        Card clone = null;
        try {
            clone = (Card) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clone;
    }

}
