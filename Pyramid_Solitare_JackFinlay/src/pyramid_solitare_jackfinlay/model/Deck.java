package pyramid_solitare_jackfinlay.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class representing Deck objects.
 *
 * @author Jack Finlay ID: 1399273
 */
public class Deck {

    public static final int DECK_SIZE = 52;
    private ArrayList<Card> deck;

    /**
     * The default constructor. Creates an empty deck.
     */
    public Deck() {
        this.deck = new ArrayList();
    }

    /**
     * Alternative constructor, used with the copy() copy constructor.
     *
     * @param deck The ArrayList representation of the deck.
     */
    public Deck( ArrayList<Card> deck ) {
        this.deck = deck;
    }

    /**
     * Copy Constructor to create a duplicate of a deck.
     *
     * @param deck The Deck to copy.
     * @return A duplicate of the passed deck.
     */
    public static Deck copy( Deck deck ) {
        ArrayList<Card> copy = new ArrayList<>();

        for ( Object aCard : deck.getDeckAsList() ) {
            Card card = (Card) aCard;
            try {
                copy.add(card.clone());
                // Creates a deep copy of the items in the master deck.
            } catch ( CloneNotSupportedException ex ) {
                Logger.getLogger(Deck.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return new Deck(copy);
    }

    /**
     * Creates and fills deck with a full set of playing cards.
     */
    public void createDeck() {
        ArrayList newDeck = new ArrayList();

        //Populate deck. 
        for ( int i = 0; i < 4; i++ ) {
            for ( int j = 1; j <= 13; j++ ) {
                newDeck.add(new Card(CardSuits.values()[i], j, this));
            }
        }

        this.deck = newDeck;
    }

    /**
     * Returns the current deck as an ArrayList.
     *
     * @return The deck as an ArrayList.
     */
    public ArrayList getDeckAsList() {
        return this.deck;
    }

    /**
     * Shuffles the specified deck.
     *
     * @param deck The deck to be shuffled.
     * @return The shuffled deck.
     */
    public static Deck shuffleDeck( Deck deck ) {
        Collections.shuffle((List<?>) deck.getDeckAsList());
        return deck;
    }

    /**
     * Adds a card to the start of the deck.
     *
     * @param card The card to be added to this deck.
     */
    public void addCard( Card card ) {
        if ( (card != null) && (deck.size() <= DECK_SIZE) ) {
            deck.add(0, card);
        }
    }

    /**
     * Removes the specified card from the deck.
     *
     * @param card The card to be removed.
     */
    public void removeCard( Card card ) {
        this.deck.remove(card);
    }

    /**
     * Returns the card from the specified index.
     *
     * @param index The index to get the card from.
     * @return The requested card.
     */
    public Card getCard( int index ) {
        if ( index < 0 || index >= deck.size() ) {
            throw new ArrayIndexOutOfBoundsException("Index is outside bounds of deck.");
        }

        return (Card) deck.get(index);
    }

    /**
     * @return The number of cards in the deck.
     */
    public int getSize() {
        return deck.size();
    }
}
