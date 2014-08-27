package pyramid_solitare_jackfinlay.model;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class DeckTest {

    Deck deck1, deck2;

    public DeckTest() {
    }

    @Before
    public void setUp() {
        deck1 = new Deck();
        deck2 = new Deck();
    }

    @After
    public void tearDown() {
        deck1 = null;
        deck2 = null;
    }

    /**
     * Test of createDeck method, of class Deck.
     */
    @Test
    public void testCreateDeck() {
        deck1.createDeck();

        assertEquals(deck1.getSize(), 52);
    }

    /**
     * Test of addCard method, of class Deck.
     */
    @Test
    public void testAddCard() {
        Card card1 = new Card();
        Card card2 = new Card();
        deck1.addCard(card1);
        deck2.addCard(card1);
        deck2.addCard(card2);

        assertTrue(deck1.getDeckAsList().contains(card1));
        assertTrue(deck2.getDeckAsList().contains(card1));
        assertTrue(deck2.getDeckAsList().contains(card2));
    }

    /**
     * Test of removeCard method, of class Deck.
     */
    @Test
    public void testRemoveCard() {
        Card card1 = new Card();
        Card card2 = new Card();
        deck1.addCard(card1);
        deck1.addCard(card2);

        assertTrue(deck1.getDeckAsList().contains(card1));
        assertTrue(deck1.getDeckAsList().contains(card2));

        deck1.removeCard(card1);
        deck1.removeCard(card2);

        assertFalse(deck1.getDeckAsList().contains(card1));
        assertFalse(deck1.getDeckAsList().contains(card2));

    }

    /**
     * Test of getCard method, of class Deck.
     */
    @Test
    public void testGetCard() {
        Card card1 = new Card();
        Card card2 = new Card();
        deck1.addCard(card1);
        deck1.addCard(card2);

        assertTrue(deck1.getDeckAsList().contains(card1));
        assertTrue(deck1.getDeckAsList().contains(card2));

        assertEquals(deck1.getCard(1), card1);
        assertEquals(deck1.getCard(0), card2);
    }

}
