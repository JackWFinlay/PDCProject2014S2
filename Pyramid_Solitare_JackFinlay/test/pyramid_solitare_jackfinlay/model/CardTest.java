package pyramid_solitare_jackfinlay.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack Finlay ID: 1399273
 */
public class CardTest {

    private static Card card1, card2;

    public CardTest() {
    }

    @Before
    public void setUp() {
        card1 = new Card();
        card2 = new Card();
    }

    @After
    public void tearDown() {
        card1 = null;
        card2 = null;
    }

    /**
     * Test of getSymbolValue method, of class Card.
     */
    @Test
    public void testGetSymbolValue() {
        card1 = new Card(CardSuits.SPADES, 1);
        card2 = new Card(CardSuits.CLUBS, 2);
        assertEquals(card1.getSymbolValue(), "♠A");
        assertEquals(card2.getSymbolValue(), "♣2");
        card1 = new Card(CardSuits.HEARTS, 11);
        card2 = new Card(CardSuits.DIAMONDS, 12);
        assertEquals(card1.getSymbolValue(), "♥J");
        assertEquals(card2.getSymbolValue(), "♦Q");
        card1 = new Card(CardSuits.HEARTS, 13);
        assertEquals(card1.getSymbolValue(), "♥K");

    }

    /**
     * Test of getCharacterValue method, of class Card.
     */
    @Test
    public void testGetCharacterValue() {
        card1 = new Card(CardSuits.SPADES, 1);
        card2 = new Card(CardSuits.CLUBS, 2);
        assertEquals(card1.getCharacterValue(), "SA");
        assertEquals(card2.getCharacterValue(), "C2");
        card1 = new Card(CardSuits.HEARTS, 11);
        card2 = new Card(CardSuits.DIAMONDS, 12);
        assertEquals(card1.getCharacterValue(), "HJ");
        assertEquals(card2.getCharacterValue(), "DQ");
        card1 = new Card(CardSuits.HEARTS, 13);
        assertEquals(card1.getCharacterValue(), "HK");

    }

    /**
     * Test of getNumericValue method, of class Card.
     */
    @Test
    public void testGetNumericValue() {
        Card[] cards = new Card[13];

        for (int i = 0; i <= 12; i++) {
            cards[i] = new Card(CardSuits.SPADES, (i+1));
        }

        for (int i = 0; i <= 12; i++) {
            assertEquals(cards[i].getNumericValue(), (i+1));
        }

    }

    /**
     * Test of isPlayable method, of class Card.
     */
    @Test
    public void testIsPlayable_NotPlayableOnContruction() {
        //Default state is false.
        assertFalse(card1.isPlayable());
        assertFalse(card2.isPlayable());
    }

    /**
     * Test of setPlayable method, of class Card.
     */
    @Test
    public void testSetPlayable_Playable() {
        assertFalse(card1.isPlayable());
        assertFalse(card2.isPlayable());

        card1.setPlayable(true);
        card1.setPlayable(true);

        assertTrue(card1.isPlayable());
        assertTrue(card1.isPlayable());
    }

    @Test
    public void testSetPlayable_UnMark() {
        //First set to playable.
        card1.setPlayable(true);
        card2.setPlayable(true);
        assertTrue(card1.isPlayable());
        assertTrue(card2.isPlayable());

        //Set to false and check.
        card1.setPlayable(false);
        card2.setPlayable(false);
        assertFalse(card1.isPlayable());
        assertFalse(card2.isPlayable());
    }

    /**
     * Test of isMatched method, of class Card.
     */
    @Test
    public void testIsMatched_NotMatchedOnCreation() {
        assertFalse(card1.isMatched());
        assertFalse(card2.isMatched());
    }

    @Test
    public void testIsMatched_Matched() {
        //Check not already matched.
        assertFalse(card1.isMatched());
        assertFalse(card2.isMatched());
        
        // Set and check is true.
        card1.setMatched();
        card2.setMatched();
        assertTrue(card1.isMatched());
        assertTrue(card1.isMatched());
        // Effectively also tests setMatched().
    }

}
