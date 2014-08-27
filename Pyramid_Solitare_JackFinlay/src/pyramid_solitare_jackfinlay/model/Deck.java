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

    public Deck() {
        this.deck = new ArrayList();
    }

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

    public static Deck shuffleDeck(Deck deck) {
        Collections.shuffle((List<?>) deck.getDeckAsList());
        return deck;
    }

    public void addCard(Card card) {
        if ((card != null) && (deck.size() <= DECK_SIZE)) {
            deck.add(0, card);
        }
    }

    public boolean removeCard(Card card) {
        boolean success = false;

        if (card != null) {
            success = this.deck.remove(card);
        }

        return success;
    }

    public Card getCard(int index) {
        if (index < 0 || index >= deck.size()) {
            throw new ArrayIndexOutOfBoundsException("Index is outside bounds of deck.");
        }

        return (Card) deck.get(index);
    }

    public int getSize() {
        return deck.size();
    }
}
