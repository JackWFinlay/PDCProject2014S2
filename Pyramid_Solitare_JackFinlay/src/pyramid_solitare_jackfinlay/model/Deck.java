package pyramid_solitare_jackfinlay.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
     * Creates and fills deck with a full set of playing cards.
     */
    public void createDeck() {
        ArrayList newDeck = new ArrayList();

        //Populate deck. 
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
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
    public static Deck shuffleDeck(Deck deck) {
        Collections.shuffle((List<?>) deck.getDeckAsList());
        return deck;
    }

    /**
     * Adds a card to the start of the deck.
     *
     * @param card The card to be added to this deck.
     */
    public void addCard(Card card) {
        if ((card != null) && (deck.size() <= DECK_SIZE)) {
            deck.add(0, card);
        }
    }

    /**
     * Removes the specified card from the deck.
     *
     * @param card The card to be removed.
     */
    public void removeCard(Card card) {
        this.deck.remove(card);
    }

    /**
     * Returns the card from the specified index.
     *
     * @param index The index to get the card from.
     * @return The requested card.
     */
    public Card getCard(int index) {
        if (index < 0 || index >= deck.size()) {
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
